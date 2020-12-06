package com.andresivan.turistadroid

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Se pone la activity en fullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //Creamos dos animaciones para darle mas vistosidad al SplashScreen
       val animacion1:Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.desplazamiento_arriba)
       val animacion2:Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.desplazamiento_abajo)
        //Rescatamos los elementos del SplashScreen
        val tituloSplashScreen:TextView = findViewById(R.id.txvTituloSplashScreen)
        val imagenSplashScreen:ImageView = findViewById(R.id.imgSplashScreen)
        //Le damos las animaciones anteriormente creadas
        tituloSplashScreen.animation=animacion2
        imagenSplashScreen.animation=animacion1

        //Creamos un handler para que despues de 4 segundos, nos muestre la actividad del login
        val handler: Handler= Handler()

        handler.postDelayed({
            val intent:Intent = Intent(this,login::class.java)
            startActivity(intent)
            finish()
        },4000)

    }


}