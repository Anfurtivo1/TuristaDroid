package com.andresivan.turistadroid.entidades.lugares

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.andresivan.turistadroid.entidades.lugares.servicios.LugarAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LugarControlador {

    /**
     * Obtendremos una lista con todos los lugares
     */
    fun obtenerLugares(context: Context) {
        val clienteRest = LugarAPI.servicio
        Log.i("Lugares","Se van a obtener todos los lugares")
        val call: Call<List<LugarDTO>> = clienteRest.obtenerLugares()


        call.enqueue((object : Callback<List<LugarDTO>> {
            override fun onResponse(call: Call<List<LugarDTO>>, response: Response<List<LugarDTO>>) {
                Log.i("Lugares","Se ha entrado a onResponse")

                if (response.isSuccessful) {

                    Log.i("Lugares", "se consiguio hacer el onResponse")
                    var lugares = (LugarMapeado.fromDTO(response.body() as MutableList<LugarDTO>)) as MutableList<Lugar>
                    for (lugar in lugares){
                        print(lugar)
                        Log.i("Lugares",lugar.nombre )

                    }
                } else {
                    Log.i("Lugares", "No se pudo hacer el onresponse")
                }
            }

            override fun onFailure(call: Call<List<LugarDTO>>, t: Throwable) {
            }
        }))
    }

    /**
     * Se añadirá un lugar
     */
    fun crearLugar(lugar: Lugar) {
        val clienteRest = LugarAPI.servicio
        val call: Call<LugarDTO> = clienteRest.crearLugar((LugarMapeado.toDTO(lugar)))
        call.enqueue((object : Callback<LugarDTO>  {

            override fun onResponse(call: Call<LugarDTO>, response: Response<LugarDTO>) {
                if (response.isSuccessful) {
                    Log.i("Lugares", "Se consiguio crear el lugar")
                } else {
                    Log.i("Lugares", "No se pudo crear el lugar")
                }
            }

            override fun onFailure(call: Call<LugarDTO>, t: Throwable) {
            }
        }))
    }


    fun actualizarLugar(lugar: Lugar) {
        val clienteRest = LugarAPI.servicio
        val albumDTO = LugarMapeado.toDTO(lugar)

        val call: Call<LugarDTO> = clienteRest.actualizarLugar(lugar.id, albumDTO)
        call.enqueue((object : Callback<LugarDTO> {

            override fun onResponse(call: Call<LugarDTO>, response: Response<LugarDTO>) {
                if (response.isSuccessful) {
                    Log.i("Lugares", "Se consiguio actualizar el lugar")

                } else {
                    Log.i("Lugares", "No se consiguio crear el lugar")
                }
            }

            override fun onFailure(call: Call<LugarDTO>, t: Throwable) {
            }
        }))

    }

    fun borrarLugar(lugar: Lugar) {
        val clienteRest = LugarAPI.servicio
        val call: Call<LugarDTO> = clienteRest.borrarLugar(lugar.id)
        call.enqueue((object : Callback<LugarDTO> {

            override fun onResponse(call: Call<LugarDTO>, response: Response<LugarDTO>) {
                if (response.isSuccessful) {
                    Log.i("Lugares", "Se consiguio borrar el lugar")
                } else {
                    Log.i("Lugares", "No se consiguio borrar el lugar")
                }
            }

            override fun onFailure(call: Call<LugarDTO>, t: Throwable) {
            }
        }))
    }


}