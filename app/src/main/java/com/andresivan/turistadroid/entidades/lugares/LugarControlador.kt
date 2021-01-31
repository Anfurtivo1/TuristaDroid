package com.andresivan.turistadroid.entidades.lugares

import android.content.Context
import android.util.Log
import com.andresivan.turistadroid.entidades.lugares.servicios.LugarAPI
import com.andresivan.turistadroid.ui.missitios.MisSitios
import com.andresivan.turistadroid.ui.missitios.SitiosListAdapter
import kotlinx.android.synthetic.main.fragment_missitios.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LugarControlador {
    lateinit var lugares:MutableList<Lugar>

    /**
     * Obtendremos una lista con todos los lugares
     */
    fun obtenerLugares(context: Context, sitiosAdapter:SitiosListAdapter) {
        val clienteRest = LugarAPI.servicio
        Log.i("Lugares","Se van a obtener todos los lugares")
        val call: Call<List<LugarDTO>> = clienteRest.obtenerLugares()

        call.enqueue((object : Callback<List<LugarDTO>> {
            override fun onResponse(call: Call<List<LugarDTO>>, response: Response<List<LugarDTO>>) {
                Log.i("Lugares","Se ha entrado a onResponse")

                if (response.isSuccessful) {
                    Log.i("Lugares", "se consiguio hacer el onResponse")
                    lugares= (LugarMapeado.fromDTO(response.body() as MutableList<LugarDTO>)) as MutableList<Lugar>
                    sitiosAdapter.notifyDataSetChanged()
                    //sitiosRecycler.setHasFixedSize(true)
                    //sitiosSwipeRefresh.isRefreshing = false

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
        val clientREST = LugarAPI.servicio
        val call: Call<LugarDTO> = clientREST.crearLugar((LugarMapeado.toDTO(lugar)))
        call.enqueue((object : Callback<LugarDTO>  {

            override fun onResponse(call: Call<LugarDTO>, response: Response<LugarDTO>) {
                Log.i("Lugares", "codigo de respuesta = "+response.code().toString())
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

    /**
     * Se actualizara un lugar en función de su id
     */
    fun actualizarLugar(lugar: Lugar) {
        val clienteRest = LugarAPI.servicio
        val LugarDTO = LugarMapeado.toDTO(lugar)

        val call: Call<LugarDTO> = clienteRest.actualizarLugar(lugar.id, LugarDTO)
        call.enqueue((object : Callback<LugarDTO> {

            override fun onResponse(call: Call<LugarDTO>, response: Response<LugarDTO>) {
                if (response.isSuccessful) {
                    Log.i("lugar", "lugarUpdate ok")

                } else {
                    Log.i("lugar", "Error: lugarUpdate isSuccessful")
                }
            }

            override fun onFailure(call: Call<LugarDTO>, t: Throwable) {
            }
        }))

    }

    fun eliminarLugar(lugar: Lugar) {
        val clientREST = LugarAPI.servicio
        val call: Call<LugarDTO> = clientREST.borrarLugar(lugar.id)
        call.enqueue((object : Callback<LugarDTO> {

            override fun onResponse(call: Call<LugarDTO>, response: Response<LugarDTO>) {
                if (response.isSuccessful) {
                    Log.i("Lugar", "LugarDelete ok")
                } else {
                    Log.i("Lugar", "Error: LugarDelete isSuccessful")
                    Log.i("Eliminar", "Error al eliminar: " + response.message())
                }
            }

            override fun onFailure(call: Call<LugarDTO>, t: Throwable) {
                Log.i("Lugar", "Error al acceder al servicio: " + t.localizedMessage)
            }
        }))
    }


}