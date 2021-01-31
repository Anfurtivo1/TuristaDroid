package com.andresivan.turistadroid.entidades.usuario

import com.google.gson.annotations.SerializedName

class UsuarioDTO (
    @SerializedName("id") val id: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("contrasena") val contrasena: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("fotoUsuario") val fotoUsuario: String,
    @SerializedName("cuentaTwitter") val cuentaTwitter: String
)