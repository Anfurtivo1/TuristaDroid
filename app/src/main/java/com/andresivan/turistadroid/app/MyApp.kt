package com.andresivan.turistadroid.app

import android.Manifest
import android.app.Application
import android.util.Log
import android.widget.Toast
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {

    companion object{
        lateinit var USUARIO_ACTIVO:Usuario
    }

    private val nombreBD = "ANDRESIVAN_MIS_LUGARES"
    private val versionBD = 1L

    var PERMISOS = false
        private set

    override fun onCreate() {
        super.onCreate()
        initRealmBD()
        initPermisos()
    }


    private fun initRealmBD() {
        Realm.init(applicationContext)
        val config = RealmConfiguration.Builder()
            .name(nombreBD)
            .schemaVersion(versionBD)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    /**
     * Comprobamos los permisos de la aplicación
     */
    fun initPermisos() {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    PERMISOS = true
                }
                if (report.isAnyPermissionPermanentlyDenied) {
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest?>?,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).withErrorListener { Toast.makeText(applicationContext, "ERROR PERMISOS ", Toast.LENGTH_SHORT).show() }
            .onSameThread()
            .check()
    }

}