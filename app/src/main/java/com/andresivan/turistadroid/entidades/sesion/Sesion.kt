package com.andresivan.turistadroid.entidades.sesion

import java.io.Serializable

open class Sesion(
    var idUsuarioActivo: String = "",
    var tiempo_acceso: String = "",
    var token: String = ""
):Serializable{

    fun fromSesion(sesion: Sesion) {
        this.idUsuarioActivo = sesion.idUsuarioActivo
        this.tiempo_acceso = sesion.tiempo_acceso
        this.token = sesion.token
    }

}