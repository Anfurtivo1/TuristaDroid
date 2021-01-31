package com.andresivan.turistadroid.entidades.fotos

import android.util.Log
import com.andresivan.turistadroid.entidades.fotos.Foto
import com.andresivan.turistadroid.entidades.fotos.FotoDTO
import com.andresivan.turistadroid.entidades.fotos.FotoMapeado
import com.andresivan.turistadroid.entidades.fotos.servicios.FotoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object FotoControlador {
    /**
     * Se añadirá una foto
     */
    fun crearFoto(foto: Foto) {
        val clienteRest = FotoAPI.servicio
        val call: Call<FotoDTO> = clienteRest.crearFoto((FotoMapeado.toDTO(foto)))
        call.enqueue((object : Callback<FotoDTO> {

            override fun onResponse(call: Call<FotoDTO>, response: Response<FotoDTO>) {
                if (response.isSuccessful) {
                    Log.i("Lugares", "Se consiguio crear la foto")
                } else {
                    Log.i("Lugares", "No se pudo crear la foto")
                }
            }

            override fun onFailure(call: Call<FotoDTO>, t: Throwable) {
            }
        }))
    }

    fun actualizarFoto(foto: Foto) {
        val clientREST = FotoAPI.servicio
        val FotoDTO = FotoMapeado.toDTO(foto)

        val call: Call<FotoDTO> = clientREST.actualizarFoto(foto.id, FotoDTO)
        call.enqueue((object : Callback<FotoDTO> {

            override fun onResponse(call: Call<FotoDTO>, response: Response<FotoDTO>) {
                if (response.isSuccessful) {
                    Log.i("Lugares", "Se ha actualizado la foto")

                } else {
                    Log.i("Lugares", "Se produció un error al borrar la foto")
                }
            }

            override fun onFailure(call: Call<FotoDTO>, t: Throwable) {
            }
        }))

    }


    fun eliminarFoto(foto: Foto) {
        val clientREST = FotoAPI.servicio
        val call: Call<FotoDTO> = clientREST.borrarFoto(foto.id)
        call.enqueue((object : Callback<FotoDTO> {

            override fun onResponse(call: Call<FotoDTO>, response: Response<FotoDTO>) {
                if (response.isSuccessful) {
                    Log.i("Lugares", "Se consiguio eliminar la foto")
                } else {
                    Log.i("Lugares", "Se produció un error al borrar la foto")
                }
            }

            override fun onFailure(call: Call<FotoDTO>, t: Throwable) {
            }
        }))
    }

}