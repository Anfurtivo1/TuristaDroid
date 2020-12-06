package com.andresivan.turistadroid.entidades.preferencias

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.usuario.UsuarioControlador.existeUsuario
import com.andresivan.turistadroid.utils.CifradorContrasena
import com.google.gson.Gson

object PreferenciasController {

    private var USER_ID: String = ""
    private lateinit var USER: Usuario

    fun comprobarSesion(context: Context): Boolean {
        // Abrimos las preferencias en modo lectura
        val prefs = context.getSharedPreferences("MisLugares", Context.MODE_PRIVATE)
        USER_ID = prefs.getString("USER_ID", "").toString()
        Log.i("Config", "Usuario ID: " + USER_ID)
        return USER_ID.isNotEmpty()
    }

    /**
     * Función que se encarga de recoger por parámetro los distintos valores pasados por parámetro para crear
     * un usuario en insertarlo en la Base de Datos
     * @param context Context
     */
    fun crearSesion(context: Context, correo: String, contrasena:String, nombreUsuario: String, fotoUsuario: String): Usuario {
        val usuario = Usuario(
            correo = correo,
            contrasena = CifradorContrasena.convertirHash(contrasena, "SHA-256")!!,
            nombre = nombreUsuario,
            fotoUsuario = fotoUsuario,
            cuentaTwitter = ""
        )

        if (existeUsuario(correo)){
           Log.i("REGISTRO", "")
        }else{
            UsuarioControlador.insert(usuario)
            Log.i("REGISTRO", "USUARIO REGISTRADO $usuario")
        }
        // Abrimos las preferemcias en modo escritura
        val prefs = context.getSharedPreferences("MisLugares", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("USER_ID", usuario.id)
        // Escribimos el usuario como JSON
        editor.putString("USER", Gson().toJson(usuario))
        editor.apply()
        return usuario
    }

    /**
     * Leemos la sesion activa
     * @param context Context
     * @return Usuario
     */
    fun leerSesion(context: Context): Usuario {
        val prefs = context.getSharedPreferences("MisLugares", Context.MODE_PRIVATE)
        return Gson().fromJson(prefs.getString("USER", ""), Usuario::class.java)
    }

}