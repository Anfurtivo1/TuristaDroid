package com.andresivan.turistadroid.entidades.fotos

<<<<<<< HEAD
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
=======
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Foto(
    @PrimaryKey var id: String = "",
    @Required var imgLugar: String = "",
    @Required var uri: String = "",
    @Required var hash: String = "",
    @Required var usuarioID: String = ""
) : RealmObject() {
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
>>>>>>> main
        usuarioID
    )
}