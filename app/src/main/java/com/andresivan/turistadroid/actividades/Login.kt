package com.andresivan.turistadroid.actividades

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController.iniciarSesionUsuario
import com.andresivan.turistadroid.entidades.sesion.Sesion
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.usuario.UsuarioControlador.selectByCorreo
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPermisos()

        login_link_registrarse.setOnClickListener { abrirRegistrarse() }
        btnIniciarSesion.setOnClickListener { iniciarSesion() }

    }
    private fun initPermisos() {
        if (!(this.application as MyApp).PERMISOS)
            (this.application as MyApp).initPermisos()
    }

    private fun abrirRegistrarse() {
        val intent = Intent(this, registrarse::class.java)
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
                if (usuario != null) {
                    Log.i("INICIO SESION", "Id usuario: "+usuario.id)
                    MyApp.USUARIO_ACTIVO = usuario
                    var sesion = Sesion(
                    idUsuarioActivo = usuario.id
                    )
                    SesionController.deleteSesion(sesion.idUsuarioActivo)
                    SesionController.insert(sesion)
                    Log.i("CREACION SESION", sesion.toString())
                    Toast.makeText(this, "ID del usuario: "+usuario.id, Toast.LENGTH_SHORT)
                }
                abrirPantallaPrincipal()
            } else {
                Toast.makeText(this, "Correo o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun abrirPantallaPrincipal() {
        val intent = Intent(this, pantallaprincipal::class.java)
        startActivity(intent)
    }


    /**
     * En este metodo lo que hacemos es que eliminamos los fragment de la pila y cuando no haya nos llama al metodo de confirmarSalir
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
     * Este es el metodo que se lanzará en el metodo anterior
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