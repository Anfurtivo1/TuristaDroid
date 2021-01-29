package com.andresivan.turistadroid.entidades.preferencias

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.usuario.UsuarioControlador.existeUsuario
import com.andresivan.turistadroid.utils.CifradorContrasena
import com.google.gson.Gson

object PreferenciasController {

    private var ID_USUARIO: String = ""
    private lateinit var USUARIO: Usuario

    fun comprobarSesion(context: Context): Boolean {
        val prefs = context.getSharedPreferences("MisLugares", Context.MODE_PRIVATE)
        ID_USUARIO = prefs.getString("USER_ID", "").toString()
        Log.i("Config", "Usuario ID: " + ID_USUARIO)
        return ID_USUARIO.isNotEmpty()
    }

    fun leerSesion(context: Context): Usuario {
        val prefs = context.getSharedPreferences("MisLugares", Context.MODE_PRIVATE)
        return Gson().fromJson(prefs.getString("USER", ""), Usuario::class.java)
    }

}