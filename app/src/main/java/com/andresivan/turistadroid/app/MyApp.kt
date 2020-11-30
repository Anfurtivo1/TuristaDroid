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
        Log.i("Configuracion", "Inicializacion de la confifuracion de MyApp")
        initRealmBD()
        Log.i("Configuracion", "Fin de la inicializacion de la configuración de MyApp")

    }

    private fun initRealmBD() {
        Log.i("Configuracion", "Inicializacion de la base de datos de RealmDB")
        Realm.init(applicationContext)
        val realmConfig = RealmConfiguration.Builder()
            .name(nombreBD)
            .schemaVersion(versionBD)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
        Log.i("Configuracion", "Fin de la inicializacion de la base de datos de RealmDB")
    }
}