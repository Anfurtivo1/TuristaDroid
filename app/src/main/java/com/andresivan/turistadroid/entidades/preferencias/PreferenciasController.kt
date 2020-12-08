package com.andresivan.turistadroid.entidades.preferencias

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.login
import com.andresivan.turistadroid.pantallaprincipal
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
    fun crearSesion(context: Context, correo: String, contrasena:String, nombreUsuario: String, fotoUsuario: String) {
        val usuario = Usuario(
            correo = correo,
            contrasena = CifradorContrasena.convertirHash(contrasena, "SHA-256")!!,
            nombre = nombreUsuario,
            fotoUsuario = fotoUsuario,
            cuentaTwitter = ""
        )

        if (existeUsuario(correo)){
           Toast.makeText(context, "Ya existe un usuario con ese correo", Toast.LENGTH_SHORT).show()
        }else{
            UsuarioControlador.insert(usuario)
            Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
            Log.i("REGISTRO", "USUARIO REGISTRADO $usuario")
        }
    }

    fun iniciarSesionUsuario(context: Context, correo: String, contrasena:String):Boolean {
        val usuario = Usuario(
            correo = correo,
            contrasena = CifradorContrasena.convertirHash(contrasena, "SHA-256")!!,
            nombre = "",
            fotoUsuario = "",
            cuentaTwitter = ""
        )
        //SesionController.removeAll()
        if (existeUsuario(correo)){
            Toast.makeText(context, "Iniciando sesión", Toast.LENGTH_SHORT).show()
            //AQUÍ iRÁ TODO LO QUE TENGAMOS QUE HACER PARA GUARDAR LA INFORMACIÓN DEL USUARIO PARA EL RESTO DE LA APLICACIÓN
            /*
            val prefs = context.getSharedPreferences("TuristaDroid", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("USER_ID", usuario.id)
            // Escribimos el usuario como JSON
            editor.putString("USER", Gson().toJson(usuario))
            editor.apply()
            //return usuario
             */
            return true
        }else{
            return false
        }
    }

    private fun abrirPreferecias (context: Context, usuario: Usuario): Usuario{
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