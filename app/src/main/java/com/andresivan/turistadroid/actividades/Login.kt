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
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController.iniciarSesionUsuario
import com.andresivan.turistadroid.entidades.sesion.Sesion
<<<<<<< Updated upstream
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.usuario.UsuarioControlador.selectByCorreo
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
=======
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.temporal.ChronoUnit


class Login : AppCompatActivity() {

    lateinit var usuarioGoogle: Usuario
    var loginGoogle:Boolean=true
    private lateinit var sesionRemota: Sesion
    private val tiempoConexionSesion = 180
    private var hayUsuarioActivo = false
    //
    private lateinit var auth: FirebaseAuth
    //
    private lateinit var clienteSignInGoogle: GoogleSignInClient
    //
    private val RC_SIGN_IN = 9001
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPermisos()
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setOnClickListener{signIn()}

        login_link_registrarse.setOnClickListener { abrirRegistrarse() }
        btnIniciarSesion.setOnClickListener { iniciarSesion() }

<<<<<<< Updated upstream
=======
        initUI()
        //
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso.
        clienteSignInGoogle = GoogleSignIn.getClient(this, gso);

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

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
            //updateUI(account)
            if (account != null) {
                usuarioGoogle= Usuario()
                usuarioGoogle.nombre=account.displayName.toString()
                usuarioGoogle.correo=account.email.toString()
                usuarioGoogle.fotoUsuario=account.photoUrl.toString()
                abrirPantallaPrincipal()
            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //updateUI(null)
        }
    }

    private fun initUI() {
        cargarSesion()
    }

    private fun cargarSesion() {
        try {
            //usuario = SesionController.getSesionActual(this)!!
            //comprobarSesionesActivas(usuario)
        }catch (ex: Exception){
            Log.i("LOGIN", "ERRORES: " + ex.localizedMessage)
        }
    }

    private fun comprobarSesionesActivas(usuario: Usuario) {
        val clienteREST = MisSitiosAPI.service
        val call: Call<SesionDTO> = clienteREST.getSesionByID(usuario.id)
        call.enqueue((object :retrofit2.Callback<SesionDTO>{
            override fun onResponse(call: Call<SesionDTO>, response: Response<SesionDTO>) {
                if (response.isSuccessful){
                    Log.i("TuristaREST", "getSesionByID RESPUESTA OK")
                    var sesionRemota = SesionMapeado.fromDTO(response.body() as SesionDTO)
                    sesionRemota = Sesion()
                    sesionRemota.fromSesion(sesionRemota)
                    // Si la obtiene comparamos
                    compararSesiones()
                }
            }

            override fun onFailure(call: Call<SesionDTO>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "ERROR - NO SE PUEDE ACCEDER AL SERVICIO: " + t.localizedMessage,
                    Toast.LENGTH_LONG)
                    .show()
            }

        }))
>>>>>>> Stashed changes
    }
    private fun initPermisos() {
        if (!(this.application as MyApp).PERMISOS)
            (this.application as MyApp).initPermisos()
    }

    private fun abrirRegistrarse() {
        val intent = Intent(this, registrarse::class.java)
        startActivity(intent)
    }

    private fun iniciarSesion() {

<<<<<<< Updated upstream
=======
        if (isFormularioRelleno()) {
            var correo = login_et_correo.text.toString()
            var contrasena = login_et_contrasena.text.toString()
            val pass = CifradorContrasena.convertirHash(contrasena)
            iniciarSesionFireBase(correo,pass.toString())
/*            var usu = Usuario("999","1","1","1","","")
            var correo = login_et_correo.text.toString()
            var contrasena = login_et_contrasena.text.toString()
            val pass = CifradorContrasena.convertirHash(contrasena)
            usuario = Usuario("1","1","1","1","","")*/

            /*UsuarioControlador.obtenerUsuarioById(usu.id)
            if (UsuarioControlador.abrir){
                MyApp.USUARIO_ACTIVO=usu
                (application as MyApp).SESION_USUARIO = usu
                abrirPantallaPrincipal()
            }else{
                print("No te pudiste meter")
            }*/

        }

        /*
>>>>>>> Stashed changes
        var CORREO = login_et_correo.text.toString()
        var CONTRASENA = login_et_contrasena.text.toString()

        if (CORREO == "" || CONTRASENA == "") {
            Toast.makeText(
                this,
                "Rellene todos los campos necesarios para iniciar sesión",
                Toast.LENGTH_SHORT
            )
        } else {
            if (iniciarSesionUsuario(this, CORREO, CONTRASENA)) {
                var usuario = selectByCorreo(CORREO)
                if (usuario != null) {
                    Log.i("INICIO SESION", "Id usuario: "+usuario.id)
                    MyApp.USUARIO_ACTIVO = usuario
                    var sesion = Sesion(
                    idUsuarioActivo = usuario.id
                    )
                    SesionController.deleteSesion(sesion.idUsuarioActivo)
                    SesionController.insert(sesion)
                    Log.i("CREACION SESION", sesion.toString())
                    Toast.makeText(this, "ID del usuario: "+usuario.id, Toast.LENGTH_SHORT)
                }
                abrirPantallaPrincipal()
            } else {
                Toast.makeText(this, "Correo o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
<<<<<<< Updated upstream
    }

    private fun abrirPantallaPrincipal() {
        val intent = Intent(this, pantallaprincipal::class.java)
=======
         */
    }

    private fun iniciarSesionFireBase(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Usuario", "signInWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Te has conectado con exito.", Toast.LENGTH_SHORT).show()
                    //MyApp.USUARIO_ACTIVO=usu
                    //(application as MyApp).SESION_USUARIO = usu
                    loginGoogle=false
                    abrirPantallaPrincipal()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Usuario", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Error en la contraseña o correo.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    private fun errorMsg() {
        Snackbar.make(
            findViewById(android.R.id.content),
            "MAIL O CORREO INCORRECTO",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun guardarSesion() {
        if (hayUsuarioActivo) {
            eliminarSesionRemotaActiva()
        }
        /*val sesion = Sesion(
            idUsuarioActivo = usuario.id,
            tiempo_acceso = Instant.now().toString(),
            token = UUID.randomUUID().toString()
        )*/
        val clienteREST = MisSitiosAPI.service
        //val call: Call<SesionDTO> = clienteREST.addSesion(SesionMapeado.toDTO(sesion))
/*        call.enqueue((object : retrofit2.Callback<SesionDTO> {

            override fun onResponse(call: Call<SesionDTO>, response: Response<SesionDTO>) {
                if (response.isSuccessful) {
                    Log.i("TuristaREST", "addSesion RESPUESTA OK")
                    (application as MyApp).SESION_USUARIO = usuario
                    abrirPantallaPrincipal()
                }
            }

            override fun onFailure(call: Call<SesionDTO>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "ERROR - NO SE PUEDE ACCEDER AL SERVICIO: " + t.localizedMessage,
                    Toast.LENGTH_LONG)
                    .show()
            }
        }))*/
    }

    private fun eliminarSesionRemotaActiva() {
        val clienteREST = MisSitiosAPI.service
        /*val call: Call<SesionDTO> = clienteREST.deleteSesion(usuario.id)
        call.enqueue((object : retrofit2.Callback<SesionDTO> {

            override fun onResponse(call: Call<SesionDTO>, response: Response<SesionDTO>) {
                if (response.isSuccessful) {
                    Log.i("TuristaREST", "deleteSesion RESPUESTA OK")
                    hayUsuarioActivo = false
                }
            }

            override fun onFailure(call: Call<SesionDTO>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "ERROR - NO SE PUEDE ACCEDER AL SERVICIO: " + t.localizedMessage,
                    Toast.LENGTH_LONG)
                    .show()
            }
        }))*/
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
        intent.putExtra("correo",usuarioGoogle.correo)
        intent.putExtra("nombre",usuarioGoogle.nombre)
        intent.putExtra("imagen",usuarioGoogle.fotoUsuario)
>>>>>>> Stashed changes
        startActivity(intent)
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