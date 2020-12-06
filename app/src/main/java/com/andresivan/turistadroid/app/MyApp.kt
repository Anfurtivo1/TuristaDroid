package com.andresivan.turistadroid.app

import android.Manifest
import android.app.Application
import android.util.Log
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {
    private val nombreBD = "ANDRESIVAN_MIS_LUGARES"
    private val versionBD = 1L
    var APP_PERMISOS = false

    override fun onCreate() {
        super.onCreate()
        Log.i("Config", "INICIALIZANDO LA CONFIGURACIÓN DE MyApp")
        initRealmBD()
        initPermisos()
        Log.i("Config", "FINALIZANDO LA CONFIGURACIÓN DE MyApp")
    }

    private fun initRealmBD() {
        Log.i("Config", "Init Realm")
        Realm.init(applicationContext)
        val config = RealmConfiguration.Builder()
            .name(nombreBD)
            .schemaVersion(versionBD) // Versión de esquema estamos trabajando, si lo cambiamos, debemos incrementar
            .deleteRealmIfMigrationNeeded() // Podemos borrar los datos que ya haya si cambiamos el esquema,
            .build()
        Realm.setDefaultConfiguration(config)
        Log.i("Config", "Fin Realm")
    }

    /**
     * Comprobamos los permisos de la aplicación
     */
    fun initPermisos() {
        Log.i("Config", "Init Permisos")
        // Indicamos el permisos y el manejador de eventos de los mismos
        Dexter.withContext(this)
            // Lista de permisos a comprobar
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET
            )
            // Listener a ejecutar
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // ccomprbamos si tenemos los permisos de todos ellos
                    if (report.areAllPermissionsGranted()) {
                        APP_PERMISOS = true
                        Toast.makeText(applicationContext, "¡Todos los permisos concedidos!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    // comprobamos si hay un permiso que no tenemos concedido ya sea temporal o permanentemente
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // abrimos un diálogo a los permisos
                        //openSettingsDialog();
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener { Toast.makeText(applicationContext, "¡Existe errores! ", Toast.LENGTH_SHORT).show() }
            .onSameThread()
            .check()
        Log.i("Config", "Fin Permisos")
    }

}