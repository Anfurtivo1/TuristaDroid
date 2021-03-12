package com.andresivan.turistadroid.entidades.fotos

import java.util.*

open class Foto(
    var id: String = "",
    var uri: String = "",
    var usuarioID: String = ""
){
    constructor(
        uri: String,
        usuarioID: String
    ) :this(
        (UUID.randomUUID().toString()),
        uri,
        usuarioID
    )
}