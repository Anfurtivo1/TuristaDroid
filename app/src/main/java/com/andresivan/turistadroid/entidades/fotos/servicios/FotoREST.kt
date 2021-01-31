package com.andresivan.turistadroid.entidades.fotos.servicios

import com.andresivan.turistadroid.entidades.fotos.FotoDTO
import retrofit2.Call
import retrofit2.http.*

interface FotoREST {

    //Crear una foto
    @POST("fotos/")
    fun crearFoto(@Body foto: FotoDTO): Call<FotoDTO>

    //Actualizar una foto por su id
    @PUT("fotos/{id}")
    fun actualizarFoto(@Path("id") id: String, @Body FotoDTO: FotoDTO): Call<FotoDTO>

    //Borrar una foto por su id
    @DELETE("fotos/{id}")
    fun borrarFoto(@Path("id") id: String): Call<FotoDTO>

}