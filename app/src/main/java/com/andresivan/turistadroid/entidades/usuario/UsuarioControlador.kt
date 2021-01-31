package com.andresivan.turistadroid.entidades.usuario

import android.util.Log
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.entidades.usuario.UsuarioDTO
import com.andresivan.turistadroid.entidades.usuario.UsuarioMapeado
import com.andresivan.turistadroid.entidades.usuario.servicios.UsuarioAPI
import com.andresivan.turistadroid.entidades.usuario.servicios.UsuarioREST
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UsuarioControlador {
    var abrir:Boolean = false

    fun crearUsuario(usuario: Usuario) {
        val clientREST = UsuarioAPI.service
        val call: Call<UsuarioDTO> = clientREST.crearUsuario((UsuarioMapeado.toDTO(usuario)))
        call.enqueue((object : Callback<UsuarioDTO>  {

            override fun onResponse(call: Call<UsuarioDTO>, response: Response<UsuarioDTO>) {
                if (response.isSuccessful) {
                    Log.i("Usuario", "UsuarioPost ok")
                } else {
                    Log.i("Usuario", "Error UsuarioPost isSeccesful")
                    Log.i("Insertar", "Error al insertar: " + response.message())
                }
            }

            override fun onFailure(call: Call<UsuarioDTO>, t: Throwable) {
                Log.i("REST", "Error al acceder al servicio: " + t.localizedMessage)
            }
        }))
    }

    fun obtenerUsuarioById(id: String) {
        val clientREST = UsuarioAPI.service
        val call: Call<UsuarioDTO> = clientREST.obtenerUsuarioById(id)
        call.enqueue((object : Callback<UsuarioDTO>  {

            override fun onResponse(call: Call<UsuarioDTO>, response: Response<UsuarioDTO>) {
                if (response.isSuccessful) {
                    abrir = true
                } else {
                    Log.i("Usuario", "Error UsuarioPost isSeccesful ")
                }
            }

            override fun onFailure(call: Call<UsuarioDTO>, t: Throwable) {
                Log.i("Usuario", "Error al acceder al servicio: " + t.localizedMessage)
            }
        }))
    }

}