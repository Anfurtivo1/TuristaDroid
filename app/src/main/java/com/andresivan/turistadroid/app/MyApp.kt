package com.andresivan.turistadroid.app

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {
    private val nombreBD = "ANDRESIVAN_MIS_LUGARES"
    private val versionBD = 1L

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
}