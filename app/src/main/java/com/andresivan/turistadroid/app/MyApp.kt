package com.andresivan.turistadroid.app

import android.Manifest
import android.app.Application
import android.util.Log
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

    var PERMISOS = false

    override fun onCreate() {
        super.onCreate()
        Log.i("Config", "INICIALIZANDO LA CONFIGURACIÓN DE MyApp")
        initRealmBD()
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

    fun initPermisos(){
        Dexter.withContext(this).withPermissions(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET
        ).withListener(
            /*
            este listener se encarga de atender a los permisos, y con sus distintas funciones de comprobar que permisos
            tenemo en ella y si son necesarios solicitarlos
             */
            object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport) {
                    if (p0.areAllPermissionsGranted()){
                        PERMISOS = true
                        Log.i("CONFIG","PERMISOS - permisos concedidos")
                    }
                }
                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }
        ).withErrorListener {
            Log.i("CONFIG","PERMISOS - Errores de Permisos")
        }.onSameThread().check()
        Log.i("CONFIG","PERMISOS - Fin de permisos")
    }
}