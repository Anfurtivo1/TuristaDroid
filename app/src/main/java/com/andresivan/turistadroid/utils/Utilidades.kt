package com.andresivan.turistadroid.utils

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

class Utilidades {
    /**
     * Función que nos permite comprobar si tenemos una conexión disponible
     * @param context Contexto actual de nuestro dispositivo
     */
    fun conexionDisponible(context: Context?): Boolean {
        if (context == null) {
            return false
        }
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        /*
        esto que vamos a hacer ahora, es muy importante, ya que vamos a hacer una accion u otra dependiendo de la
        versión del SDK que tenga nuestro dispositivo, si la versión del SDK equivale o es superios
        a la version del api superior a la nº 29, es decir un Android 10, hará una cosa, y si es inferior hará otra.
        Esto es debido a los cambios internos que Google está haciendo a Android. Esto ya se comento en una de las
        clases que hemos dado
         */
        //la idea escribir el log viene de el proyecto que estás haciendo al mismo tiempo que nosotros
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i("Internet", "CONEXIÓN CON LOS DATOS MÓVILES")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i("Internet", "CONEXIÓN A RED WI-FI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i("Internet", "CONEXIÓN CABLEADA")
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                Log.i("Internet", "ActiveNetworkInfo.isConnected")
                return true
            }
        }
        Log.i("Internet", "Sin conexión")
        return false
    }

    /**
     *Función que nos permite comprobar si tenemos el GPS disponible
     */
    fun gpsDisponible(context: Context?): Boolean {
        val locationManager = context?.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        val estadoGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        return if (!estadoGPS) {
            Log.i("GPS", "GPS DESACTIVADO")
            false
        } else {
            Log.i("GPS", "GPS ACTIVADO")
            true
        }
    }

    fun escribirCorreo(
        activity: FragmentActivity?,
        destinatario: String = "",
        remitente: String = "",
        asunto: String = "",
        texto: String = ""
    ) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(destinatario))
        intent.putExtra(Intent.EXTRA_CC, remitente)
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
        intent.putExtra(Intent.EXTRA_TEXT, texto)
        try {
            activity?.startActivity(Intent.createChooser(intent, "Enviar usando..."))
        } catch (e: Exception) {
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Función que abre una dirección url o enlace, esto nos puede valer para abrir las url de las redes sociales
     * del usuario
     */
    fun abrirEnlace(activity: FragmentActivity?, enlace: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(enlace))
        activity!!.startActivity(intent)
    }
}