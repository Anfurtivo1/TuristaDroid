package com.andresivan.turistadroid.entidades.lugares

import com.google.gson.annotations.SerializedName

/*
    var id: String = "",
    var nombre: String = "",
    var tipo: String = "Poblacion",
    var fecha: String = "",
    var latitud: String = "",
    var altitud: String = "",
    var imgID: String = "",
    var valoracion: Int = 0,
    var fav: Boolean = false,
    var usuarioID: String = ""
 */

class LugarDTO(
    @SerializedName("id") val id: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("fecha") val fecha:String,
    @SerializedName("latitud") val latitud: String,
    @SerializedName("longitud") val longitud: String,
    @SerializedName("imgID") val imgID: String,
    @SerializedName("valoracion") val valoracion: Int,
    @SerializedName("fav") val fav: Boolean,
    @SerializedName("usuarioID") val usuarioID: String
)