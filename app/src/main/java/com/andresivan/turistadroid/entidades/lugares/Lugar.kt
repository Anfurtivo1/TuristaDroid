package com.andresivan.turistadroid.entidades.lugares

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

class Lugar(
    @PrimaryKey var id: String = "",
    @Required var nombre: String = "",
    @Required var tipo: String = "Poblacion",
    @Required var fecha: String = "",
    @Required var latitud: String = "",
    @Required var longitud: String = "",
    var valoracion: Int = 0,
    var favorito: Boolean = false,
    var usuarioID: String = ""
) : RealmObject() {

    constructor(
        nombre: String,
        tipo: String,
        fecha: String,
        latitud: String,
        longitud: String,
        valoracion: Int,
        favorito: Boolean,
        usuarioID: String
    ) : this((UUID.randomUUID().toString()), nombre, tipo, fecha, latitud, longitud, valoracion, favorito, usuarioID)

    override fun toString(): String {
        return "Lugar(id='$id', nombre='$nombre', tipo='$tipo', fecha='$fecha', latitud='$latitud', longitud='$longitud', valoracion=$valoracion, favorito=$favorito, usuarioID='$usuarioID')"
    }


}