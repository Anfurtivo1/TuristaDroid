package com.andresivan.turistadroid.entidades.sesion

import com.google.gson.annotations.SerializedName

class SesionDTO (
    @SerializedName("idUsuarioActivo") val idUsuarioActivo: String,
    @SerializedName("tiempo_acceso") val tiempo_acceso: String,
    @SerializedName("token") val token: String
)