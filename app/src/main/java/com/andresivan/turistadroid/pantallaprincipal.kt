package com.andresivan.turistadroid

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.hardware.Camera
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_miperfil.*
import kotlinx.android.synthetic.main.nav_header_main.*


class pantallaprincipal : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    //Variables para la linterna
    lateinit var camera: Camera
    var parameters: Camera.Parameters? = null
    var isFlash = false
    var estado = true
    var permisos:Boolean = false
    lateinit var USUARIO:Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantallaprincipal)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Boton para abrir la linterna aún no incorporado", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

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
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun encenderLinterna() {

        val camManager =
            getSystemService(CAMERA_SERVICE) as CameraManager
        val cameraId =
            camManager.cameraIdList[0]
        if (estado){
            camManager.setTorchMode(cameraId, true)
            estado=false
        }else{
            camManager.setTorchMode(cameraId, false)
            estado=true
        }
    }

}
