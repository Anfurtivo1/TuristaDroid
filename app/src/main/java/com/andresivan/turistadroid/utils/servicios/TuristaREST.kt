package com.andresivan.turistadroid.utils.servicios

import com.andresivan.turistadroid.entidades.fotos.FotoDTO
import com.andresivan.turistadroid.entidades.lugares.LugarDTO
import com.andresivan.turistadroid.entidades.sesion.SesionDTO
import com.andresivan.turistadroid.entidades.usuario.UsuarioDTO
import retrofit2.Call
import retrofit2.http.*

/*
Estructura JSON
{
  "usuarios": [
    {
    }
  ],
  "lugares": [
    {
    }
  ],
  "fotos":[
    {
    }
  ],
  "sesion_usuario":[
    {
    }
  ]
}
 */

interface TuristaREST {


//****************************** SESIONES ******************************

    // Añadimos una nueva sesión
    @POST("sesion_usuario/")
    fun addSesion(@Body sesion_usuario: SesionDTO): Call<SesionDTO>

    //Recuperamos la sesion del usuario utilizando su ID
    @GET("sesion_usuario/{id}")
    fun getSesionByID(
        @Path("id") id:String
    ): Call<SesionDTO>

    //Modificamos la sesion de un usuario parametrizado por ID
    @PUT("sesion_usuario/{id}")
    fun updateSesion(
        @Path("id") id: String,
        @Body sesion_usuario: SesionDTO
    ): Call<SesionDTO>

    //Elimina la sesion de un usuario parametrizado por ID
    @DELETE("sesion_usuario/{id}")
    fun deleteSesion(@Path("id") id:String
    ): Call<SesionDTO>

//**********************************************************************

//****************************** USUARIOS ******************************

    //Añadimos un nuevo usuario
    @POST("usuarios/")
    fun addUsuario(@Body usuarios: UsuarioDTO): Call<UsuarioDTO>

    //Obtenemos un usuario por su parámetro
    @GET("usuarios/{id}")
    fun getUsuarioByID(
        @Path("id") id: String
    ): Call<UsuarioDTO>

    //Modificamos la sesion de un usuario parametrizado por ID
    @PUT("usuarios/{id}")
    fun updateUsuario(
        @Path("id") id: String,
        @Body sesion_usuario: UsuarioDTO
    ): Call<UsuarioDTO>

//**********************************************************************

//******************************* LUGARES ******************************

    @POST("lugares/")
    fun addLugar(
        @Body lugarDTO: LugarDTO
    ): Call<LugarDTO>

    @GET("lugares/")
    fun getAllLugares():Call<List<LugarDTO>>

    @GET("lugares/")
    fun getAllLugaresByIdUsuario(
        @Query("usuarioID") usuarioID: String
    ): Call<List<LugarDTO>>

    @PUT("lugares/id")
    fun updateLugar(
        @Path("id") id:String,
        @Body lugarDTO: LugarDTO
    ): Call<LugarDTO>

    @DELETE("lugares/{id}")
    fun deleteLugar(
        @Path("id") id: String
    ): Call<LugarDTO>

//**********************************************************************

//******************************** FOTOS *******************************

    @POST("fotos/")
    fun addFoto(
        @Body fotoDTO: FotoDTO
    ): Call<FotoDTO>

    @GET("fotos/{id}")
    fun getFotoByID(
        @Path("id") id: String
    ): Call<LugarDTO>

    @PUT("fotos/{id}")
    fun updateFoto(
        @Path("id") id: String,
        @Body fotoDTO: FotoDTO
    ): Call<FotoDTO>

    @GET("fotos/")
    fun getAllFotosByUserID(
        @Query("usuarioID") usuarioID: String
    ): Call<List<FotoDTO>>

    @DELETE("fotos/{id}")
    fun deleteFoto(
        @Path("id") id: String
    ): Call<FotoDTO>

//**********************************************************************
}