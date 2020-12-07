package com.andresivan.turistadroid

import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController.iniciarSesionUsuario
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController.leerSesion
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador.selectByCorreo
import com.andresivan.turistadroid.utils.CifradorContrasena
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    //private lateinit var USER: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPermisos()

        login_link_registrarse.setOnClickListener { abrirRegistrarse() }
        btnIniciarSesion.setOnClickListener { iniciarSesion() }

    }


/*
    private fun leerSesion() {
        USER = (this.application as MyApp).SESION_USUARIO
    }
*/
    private fun initPermisos() {
        if (!(this.application as MyApp).PERMISOS)
            (this.application as MyApp).initPermisos()
    }

    private fun abrirRegistrarse() {
        val intent: Intent = Intent(this, registrarse::class.java)
        startActivity(intent)
    }

    private fun iniciarSesion() {

        var CORREO = login_et_correo.text.toString()
        var CONTRASENA = login_et_contrasena.text.toString()

        if (CORREO == "" || CONTRASENA == "") {
            Toast.makeText(
                this,
                "Rellene todos los campos necesarios para iniciar sesión",
                Toast.LENGTH_SHORT
            )
        } else {
            if (iniciarSesionUsuario(this, CORREO, CONTRASENA)) {
                var usuario = selectByCorreo(CORREO)
                abrirPantallaPrincipal()
            } else {
                Toast.makeText(this, "Correo o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun abrirPantallaPrincipal() {
        val intent: Intent = Intent(this, pantallaprincipal::class.java)
        startActivity(intent)
    }


    /**
     * Quitamos fragment apilados, y si no hay salimos
     */
    override fun onBackPressed() {
        try {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStackImmediate()
            else
                confirmarSalir()
        } catch (ex: Exception) {
            confirmarSalir()
        }
    }

    /**
     * Mensaje para confirmar para salir
     */
    fun confirmarSalir() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.app_name))
            .setMessage(getString(R.string.Salir))
            .setPositiveButton(getString(R.string.Si)) { dialog, which -> finish() }
            .setNegativeButton(getString(R.string.No), null)
            .show()
    }

}