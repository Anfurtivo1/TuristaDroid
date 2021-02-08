package com.andresivan.turistadroid.actividades

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.sesion.Sesion
import com.andresivan.turistadroid.entidades.sesion.SesionDTO
import com.andresivan.turistadroid.entidades.sesion.SesionMapeado
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.utils.CifradorContrasena
import com.andresivan.turistadroid.utils.servicios.MisSitiosAPI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit


class Login : AppCompatActivity() {

    lateinit var usuario: Usuario
    //
    private lateinit var auth: FirebaseAuth
    //
    private lateinit var clienteSignInGoogle: GoogleSignInClient
    //
    private val RC_SIGN_IN = 9001
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPermisos()
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setOnClickListener{signIn()}//Logearse por google

        login_link_registrarse.setOnClickListener { abrirRegistrarse() }
        btnIniciarSesion.setOnClickListener { iniciarSesion() }//Logearse por correy contraseña
        //btnIniciarSesionMovil.setOnClickListener {verificacionMovil()}

        //
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso.
        clienteSignInGoogle = GoogleSignIn.getClient(this, gso)

    }

/*    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }*/

    private fun signIn() {
        val signInIntent: Intent = clienteSignInGoogle.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            if (account != null) {
                usuario= Usuario()
                usuario.nombre=account.displayName.toString()
                usuario.correo=account.email.toString()
                usuario.fotoUsuario=account.photoUrl.toString()
                abrirPantallaPrincipal()
            }
        } catch (e: ApiException) {
        }
    }

    private fun initPermisos() {
        if (!(this.application as MyApp).PERMISOS)
            (this.application as MyApp).initPermisos()
    }

    private fun abrirRegistrarse() {
        val intent = Intent(this, Registrarse::class.java)
        startActivity(intent)
    }

    private fun iniciarSesion() {

        if (isFormularioRelleno()) {
            var correo = login_et_correo.text.toString()
            var contrasena = login_et_contrasena.text.toString()
            val pass = CifradorContrasena.convertirHash(contrasena)
            iniciarSesionFireBase(correo, pass.toString())
        }
    }

    private fun iniciarSesionFireBase(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Usuario", "signInWithEmail:success")
                    val user = auth.currentUser
                    usuario = Usuario()
                    usuario.nombre= auth.currentUser?.displayName.toString()
                    usuario.correo = auth.currentUser?.email.toString()
                    usuario.fotoUsuario = "https://upload.wikimedia.org/wikipedia/commons/9/9b/Choloepus_didactylus_2_-_Buffalo_Zoo.jpg"
                    Toast.makeText(baseContext, "Te has conectado con exito.", Toast.LENGTH_SHORT).show()
                    MyApp.loginGoogle=false
                    abrirPantallaPrincipal()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Usuario", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Error en la contraseña o correo.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun isFormularioRelleno(): Boolean {

        var valorDevuelto = true

        if (login_et_correo.text!!.isEmpty()) {
            login_et_correo.error = "Campo vacío"
            valorDevuelto = false
        }

        if (login_et_contrasena.text!!.isEmpty()) {
            login_et_contrasena.error = "Campo vacío"
            valorDevuelto = false
        }

        return valorDevuelto

    }

    private fun abrirPantallaPrincipal() {
        val intent = Intent(this, PantallaPrincipal::class.java)
        buscarUsuarioLogin()
        intent.putExtra("correo",usuario.correo)
        intent.putExtra("nombre",usuario.nombre)
        intent.putExtra("imagen",usuario.fotoUsuario)
        startActivity(intent)
    }

    private fun buscarUsuarioLogin(){
        val db = Firebase.firestore
        db.collection("Usuarios")
            .whereEqualTo("correo", usuario.correo)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    usuario.correo=document.data.getValue("Correo").toString()
                    usuario.nombre=document.data.getValue("Nombre").toString()
                    usuario.fotoUsuario=document.data.getValue("FotoUsuario").toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.w("", "Error getting documents: ", exception)
            }
    }


    /**
     * En este metodo lo que hacemos es que eliminamos los fragment de la pila y cuando no haya nos llama al metodo de confirmarSalir
     */
    override fun onBackPressed() {
        try {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStackImmediate()
            else
                confirmarSalir()
        } catch (ex: Exception) {
            confirmarSalir()
        }
    }

    /**
     * Este es el metodo que se lanzará en el metodo anterior
     */
    fun confirmarSalir() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.app_name))
            .setMessage(getString(R.string.Salir))
            .setPositiveButton(getString(R.string.Si)) { dialog, which -> finish() }
            .setNegativeButton(getString(R.string.No), null)
            .show()
    }

}