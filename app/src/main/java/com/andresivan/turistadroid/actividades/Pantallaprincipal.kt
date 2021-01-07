package com.andresivan.turistadroid.actividades

import android.content.Intent
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.utils.Utilidades
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nav_header_main.*


class pantallaprincipal : AppCompatActivity() {

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

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.pantallaprincipal, menu)

        //Este es el método para rellenar la foto el nombre y el correo del usuario
        initUI()
        return true
    }

    private fun initUI() {
        for (sesion in SesionController.selectAll()!!){
            Log.i("Sesion",sesion.toString())

            USUARIO = UsuarioControlador.selectById(sesion.idUsuarioActivo)!!

            Log.i("Mi Perfil", USUARIO.toString())

            Log.i("Mi perfil", "NOMBRE USUARIO: "+USUARIO.nombre)
            Log.i("Mi perfil", "CORREO ELECTRONICO USUARIO: "+ USUARIO.correo)
            Log.i("Mi perfil", "NOMBRE_FOTO: "+ USUARIO.fotoUsuario)


            nav_header_nombre_usuario.text = USUARIO.nombre
            nav_header_correo.text = USUARIO.correo
            //Picasso.get().load("https://ichef.bbci.co.uk/news/640/cpsprodpb/4172/production/_108545761_mediaitem108545760.jpg").resize(150, 150).into(nav_header_imagen)

            Picasso.get().load((this.filesDir?.absolutePath) +"/"+USUARIO.fotoUsuario).resize(150, 150).into(nav_header_imagen)
        }
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
            R.id.CerrarSesion -> {

                cerrarSesion()

                true
            }

            R.id.SacarDatos ->{
                val utils= Utilidades()
                val listaUsuario=UsuarioControlador.todosUsuarios()
                utils.convertirListaJSON(listaUsuario)

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
     * Este metodo carga la pantalla del login y simula el cierre de sesion
     */
    private fun cerrarSesion(){
        val intent = Intent(this, login::class.java)
        startActivity(intent)
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

}
