package com.andresivan.turistadroid.entidades.fotos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Foto(
    @PrimaryKey
    var id: String = "",
    @Required
    var imgLugar: String = "",
    @Required
    var uri: String = "",
    @Required
    var hash: String = "",
    @Required
    var usuarioID: String = ""
) : RealmObject() {
    constructor(imgLugar: String, uri: String, hash: String, usuarioID: String) :
            this((UUID.randomUUID().toString()), imgLugar, uri, hash, usuarioID)
}