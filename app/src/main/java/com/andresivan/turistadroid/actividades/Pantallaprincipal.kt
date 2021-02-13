package com.andresivan.turistadroid.actividades

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.entidades.sesion.SesionDTO
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.actividades.Login
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.utils.ABase64
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PantallaPrincipal : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var estado = true
    var USUARIO:Usuario = Usuario()
    //
    private lateinit var auth: FirebaseAuth
    private lateinit var clienteSignInGoogle: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantallaprincipal)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.ndCercaDeMi, R.id.ndMisSitios, R.id.ndMiPerfil
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        USUARIO.nombre = intent.getStringExtra("nombre").toString()
        USUARIO.correo = intent.getStringExtra("correo").toString()
        USUARIO.fotoUsuario = intent.getStringExtra("imagen").toString()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.pantallaprincipal, menu)

        //Este es el método para rellenar la foto el nombre y el correo del usuario
        initUI()
        return true
    }

    private fun initUI() {

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)
        val navUsername: TextView = headerView.findViewById(R.id.nav_header_nombre_usuario)
        val navCorreo: TextView = headerView.findViewById(R.id.nav_header_correo)
        val navImagen: ImageView = headerView.findViewById(R.id.nav_header_imagen)
        //var imagen: Bitmap? =ABase64.toBitmap(USUARIO.fotoUsuario)

        if (!MyApp.loginGoogle){
            navUsername.text = USUARIO.nombre
            navCorreo.text = USUARIO.correo
            //val decodedByte = Base64.decode(USUARIO.fotoUsuario, Base64.DEFAULT)
            //val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
            //Picasso.get().load(bitmap.toString()).resize(200,200).into(navImagen)
        }else{
            navUsername.text = USUARIO.nombre
            navCorreo.text = USUARIO.correo
            //Picasso.get().load(imagen.toString()).resize(200,200).into(navImagen)
        }


        navImagen.setOnClickListener{cerrarSesion()}

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.EncenderLinterna -> {
                encenderLinterna()
                val duracion = Toast.LENGTH_SHORT
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * En este metodo lo que vamos a hacer es encender la linterna
     */
    private fun encenderLinterna() {
        //Vamos a crear un camManager para poder usar la camara
        val camManager =
            getSystemService(CAMERA_SERVICE) as CameraManager
        //La camara que usaremos esta en la posicion 0 que es la trasera
        val cameraId =
            camManager.cameraIdList[0]
        //El camManager pondrá la camara a true para que se encienda y a false para que se apague
        if (estado){
            camManager.setTorchMode(cameraId, true)
            estado=false
        }else{
            camManager.setTorchMode(cameraId, false)
            estado=true
        }
    }

    private fun cerrarSesion() {
        // Firebase sign out
        auth = Firebase.auth
        auth.signOut()

        // Google sign out
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        clienteSignInGoogle = GoogleSignIn.getClient(this, gso)
        clienteSignInGoogle.signOut()

        finishAffinity()


    }

}
