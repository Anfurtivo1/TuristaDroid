package com.andresivan.turistadroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        login_link_registrarse.setOnClickListener { abrirRegistrarse() }

    }


    private fun abrirRegistrarse(){
        val intent: Intent = Intent(this,registrarse::class.java)
        startActivity(intent)
    }

}