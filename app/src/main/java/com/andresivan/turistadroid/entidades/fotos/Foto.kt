package com.andresivan.turistadroid.entidades.fotos

import java.util.*

open class Foto(
    var id: String = "",
    var imgLugar: String = "",
    var uri: String = "",
    var hash: String = "",
    var usuarioID: String = ""
){
    constructor(
        imgLugar: String,
        uri: String,
        hash: String,
        usuarioID: String
    ) :this(
        (UUID.randomUUID().toString()),
        imgLugar,
        uri,
        hash,
        usuarioID
    )
}