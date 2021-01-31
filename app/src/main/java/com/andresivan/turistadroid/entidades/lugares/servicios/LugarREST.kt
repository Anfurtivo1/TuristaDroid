package com.andresivan.turistadroid.entidades.lugares.servicios

import com.andresivan.turistadroid.entidades.lugares.LugarDTO
import retrofit2.Call
import retrofit2.http.*

interface LugarREST {
    //Obtener todos los lugares
    @GET("albums/")
    fun obtenerLugares(): Call<List<LugarDTO>>

    //Crear un lugar
    @POST("albums/")
    fun crearLugar(@Body lugar: LugarDTO): Call<LugarDTO>

    //Actualizar un lugar por su id
    @PUT("albums/{id}")
    fun actualizarLugar(@Path("id") id: String, @Body lugarDTO: LugarDTO): Call<LugarDTO>

    //Borrar un lugar por su id
    @DELETE("albums/{id}")
    fun borrarLugar(@Path("id") id: String): Call<LugarDTO>
}
