package com.andresivan.turistadroid.entidades.usuario.servicios

import com.andresivan.turistadroid.entidades.usuario.UsuarioDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioREST {

    //Obtenemos un usuario por su parámetro//correo
    @GET("users/{id}")
    fun obtenerUsuarioById(@Path("id") id: String): Call<UsuarioDTO>

    //Añadimos un nuevo usuario
    @POST("users/")
    fun crearUsuario(@Body usuarios: UsuarioDTO): Call<UsuarioDTO>



}