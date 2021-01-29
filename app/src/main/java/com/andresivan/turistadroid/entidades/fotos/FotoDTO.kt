package com.andresivan.turistadroid.entidades.fotos

import com.google.gson.annotations.SerializedName

class FotoDTO(
    @SerializedName("id") val id: String,
    @SerializedName("imgLugar") val imgLugar: String,
    @SerializedName("uri") val uri: String,
    @SerializedName("hash") val hash: String,
    @SerializedName("usuarioID") val usuarioID: String
)