package com.andresivan.turistadroid.entidades.preferencias

import android.content.Context
import android.util.Log
<<<<<<< HEAD
import com.andresivan.turistadroid.entidades.usuario.Usuario
=======
import android.widget.Toast
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.usuario.UsuarioControlador.existeUsuario
import com.andresivan.turistadroid.utils.CifradorContrasena
>>>>>>> main
import com.google.gson.Gson

object PreferenciasController {

<<<<<<< HEAD
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

=======
    /**
     * Función que se encarga de recoger por parámetro los distintos valores pasados por parámetro para crear
     * un usuario en insertarlo en la Base de Datos
     * @param context Context
     */
    fun crearSesion(context: Context, correo: String, contrasena:String, nombreUsuario: String, fotoUsuario: String) {
        val usuario = Usuario(
            correo = correo,
            contrasena = CifradorContrasena.convertirHash(contrasena, "SHA-256")!!,
            nombre = nombreUsuario,
            fotoUsuario = fotoUsuario,
            cuentaTwitter = ""
        )

        if (existeUsuario(correo, contrasena)){
           Toast.makeText(context, "YA EXISTE UN USUARIO CON ESE CORREO", Toast.LENGTH_SHORT).show()
        }else{
            UsuarioControlador.insert(usuario)
            Toast.makeText(context, "REGISTRADO", Toast.LENGTH_SHORT).show()
        }
    }

    fun iniciarSesionUsuario(context: Context, correo: String, contrasena:String):Boolean {
        return if (existeUsuario(correo, contrasena)){
            Toast.makeText(context, "SESIÓN INICIADA", Toast.LENGTH_SHORT).show()
            true
        }else{
            false
        }
    }
>>>>>>> main
}