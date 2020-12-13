package com.andresivan.turistadroid.entidades.sesion

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Sesion(
    @PrimaryKey var id: String = "",
    var idUsuarioActivo: String = ""
):RealmObject(){
    constructor(
        idUsuarioActivo: String
    ):
            this(
                (UUID.randomUUID().toString()),
                idUsuarioActivo
            )

    override fun toString(): String {
        return "Sesion(id =  '$id', idUsuarioActivo = '$idUsuarioActivo')"
    }
}