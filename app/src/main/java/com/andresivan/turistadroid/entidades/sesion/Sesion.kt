package com.andresivan.turistadroid.entidades.sesion

<<<<<<< HEAD
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

=======
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
>>>>>>> main
}