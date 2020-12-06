package com.andresivan.turistadroid.entidades.fotos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Foto(
    @PrimaryKey var id: String = "",
    var foto: String = ""
): RealmObject(){
    
    constructor(
        foto: String
    ): this(
        (UUID.randomUUID().toString()),
        foto
    )

    override fun toString(): String {
        return "Lugar(id='$id', foto='$foto'"
    }
}