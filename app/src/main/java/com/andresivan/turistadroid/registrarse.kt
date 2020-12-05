package com.andresivan.turistadroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.utils.CifradorContrasena
import kotlinx.android.synthetic.main.activity_registrarse.*

class registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        btnRegistrarse.setOnClickListener { registrarUsuario() }
    }

    private fun registrarUsuario() {
        var contrasena: String = registrarse_et_contrasena.text.toString()
        var correo: String = registrarse_et_correo.text.toString()
        
        if (contrasena == "" || correo == ""){
            Toast.makeText(this,"Rellene todos los campos",100)
        }else{
            Log.i("REGISTRARSE", correo)
            Log.i("REGISTRARSE", "CONTRASEÑA SIN CIFRAR$contrasena")
            val usuario = Usuario(
                correo,
                contrasena = CifradorContrasena.convertirHash(contrasena, "SHA-256")!!,
                nombre = "",
                fotoUsuario = "",
                cuentaTwitter = ""
            )
            UsuarioControlador.insert(usuario)
            Log.i("REGISTRO", "USUARIO REGISTRADO$usuario")
/*
            val usuario2 = Usuario(
                correo = "andres",
                contrasena = "",

            )

            usuario2: Usuario = UsuarioControlador.selectByCorreo("andres")!!
            Log.i("REGISTRO",
                "PROBANDO UNA SELECT POR EL CORREO DEL USUARIO, EN ESTE CASO BUSCAMOS A 'andres' $usuario2"
            )
            */
        }
    }
}