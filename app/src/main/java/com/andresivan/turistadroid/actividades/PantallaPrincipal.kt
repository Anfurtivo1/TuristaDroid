package com.andresivan.turistadroid.actividades

import android.content.Intent
import android.hardware.camera2.CameraManager
import android.os.Bundle
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
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.app.MyApp.Companion.USUARIO_ACTIVO
import com.andresivan.turistadroid.entidades.sesion.SesionDTO
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.utils.ABase64
import com.andresivan.turistadroid.utils.servicios.MisSitiosAPI
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class PantallaPrincipal : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var estado = true
    lateinit var USUARIO:Usuario

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

        leerSesionUsuarioActivo()

    }

    private fun leerSesionUsuarioActivo() {
        USUARIO = (this.application as MyApp).SESION_USUARIO
        USUARIO_ACTIVO = USUARIO
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.pantallaprincipal, menu)

        //Este es el método para rellenar la foto el nombre y el correo del usuario
        initUI()
        return true
    }

    private fun initUI() {
        /*for (sesion in SesionController.selectAll()!!){
            Log.i("Sesion",sesion.toString())
            USUARIO = UsuarioControlador.selectById(sesion.idUsuarioActivo)!!
            nav_header_nombre_usuario.text = USUARIO.nombre
            nav_header_correo.text = USUARIO.correo
            Picasso.get().load((this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath) +USUARIO.fotoUsuario).into(nav_header_imagen)

        }*/

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)
        val navUsername: TextView = headerView.findViewById(R.id.nav_header_nombre_usuario)
        val navCorreo: TextView = headerView.findViewById(R.id.nav_header_correo)
        val navImagen: ImageView = headerView.findViewById(R.id.nav_header_imagen)

        navUsername.text = USUARIO.nombre
        navCorreo.text = USUARIO.correo

        val avatar = File(
            ABase64.fromTempUri(
                ABase64.toBitmap(USUARIO.fotoUsuario)!!,
                applicationContext
            ).path!!
        )

        Picasso.get().load(avatar).resize(100,100).into(navImagen)

        navImagen.setOnClickListener{cerrarSesion()}

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.EncenderLinterna -> {

                encenderLinterna()

                val duracion = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, R.string.EncenderLinterna, Toast.LENGTH_SHORT)
                toast.show()
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
        val clienteREST = MisSitiosAPI.service
        val call: Call<SesionDTO> = clienteREST.deleteSesion(USUARIO.id)
        call.enqueue((object :Callback<SesionDTO>{
            override fun onResponse(call: Call<SesionDTO>, response: Response<SesionDTO>) {
                if (response.isSuccessful) {
                    Log.i("REST", "sesionDelete ok")
                    val login = Intent(applicationContext, Login::class.java)
                    startActivity(login)
                    finish()
                }
            }
            override fun onFailure(call: Call<SesionDTO>, t: Throwable) {
                Toast.makeText(applicationContext,
                    "ERROR - NO SE PUEDE ACCEDER AL SERVICIO: " + t.localizedMessage,
                    Toast.LENGTH_LONG)
                    .show()
            }
        }))
    }

}
