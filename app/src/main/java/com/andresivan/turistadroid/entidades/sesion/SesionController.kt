package com.andresivan.turistadroid.entidades.sesion

import android.content.Context
import android.util.Log
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import java.lang.Exception

object SesionController {

    fun getSesionActual(context: Context): Usuario?{
        if (PreferenciasController.comprobarSesion(context)){
            try{
                return PreferenciasController.leerSesion(context)
            } catch (ex: Exception) {
                Log.i("SESION", "ERROR EN LA LECTURA DE SESION" + ex.localizedMessage)
                return null
            }
        }
        return null
    }

}