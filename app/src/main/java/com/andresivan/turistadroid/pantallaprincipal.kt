package com.andresivan.turistadroid

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Camera
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class pantallaprincipal : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var camera: Camera
    var parameters: Camera.Parameters? = null
    var isFlash = false
    var estado = true
    var permisos:Boolean = false

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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.EncenderLinterna -> {

                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1000)
                } else {
                    if (getApplicationContext().getPackageManager()
                            .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
                    ) {
                        camera = Camera.open();
                        parameters = camera.getParameters();
                        isFlash = true;
                    }

                }

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

    fun encenderLinterna() {

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
