package com.andresivan.turistadroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.andresivan.turistadroid.app.MyApp
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPermisos()

        login_link_registrarse.setOnClickListener { abrirRegistrarse() }
        btnIniciarSesion.setOnClickListener { abrirPantallaPrincipal() }

    }

    private fun initPermisos() {
        if (!(this.application as MyApp).PERMISOS)
            (this.application as MyApp).initPermisos()
    }

    private fun abrirRegistrarse(){
        val intent: Intent = Intent(this,registrarse::class.java)
        startActivity(intent)
    }

    private fun abrirPantallaPrincipal(){
        val intent: Intent = Intent(this,pantallaprincipal::class.java)
        startActivity(intent)
    }

}