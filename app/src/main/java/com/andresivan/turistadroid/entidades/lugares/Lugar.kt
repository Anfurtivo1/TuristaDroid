package com.andresivan.turistadroid.entidades.lugares
/*
import com.andresivan.turistadroid.entidades.fotos.Foto
import io.realm.RealmList
*/
import com.andresivan.turistadroid.entidades.fotos.Foto
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Lugar(
    @PrimaryKey
    var id: String = "",
    @Required
    var nombre: String = "",
    @Required
    var tipo: String = "Poblacion",
    @Required
    var fecha: String = "",
    @Required
    var latitud: String = "",
    @Required
    var longitud: String = "",
    @Required
    var imgID: String = "",
    var valoracion: Int = 0,
    var fav: Boolean = false,
    @Required var usuarioID: String = ""
) : RealmObject() {

    constructor(
        nombre: String,
        tipo: String,
        fecha: String,
        latitud: String,
        longitud: String,
        imgID: String,
        valoracion: Int,
        fav: Boolean,
        usuarioID: String
    ) :
            this(
                (UUID.randomUUID().toString()),
                nombre,
                tipo,
                fecha,
                latitud,
                longitud,
                imgID,
                valoracion,
                fav,
                usuarioID
            )

    override fun toString(): String {
        return "Lugar(id='$id', nombre='$nombre', tipo='$tipo', fecha='$fecha', latitud='$latitud', longitud='$longitud', imgID='$imgID', favorito=$fav, valoracion=$valoracion, usuarioID='$usuarioID')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Lugar) return false
        if (id != other.id) return false
        if (nombre != other.nombre) return false
        if (tipo != other.tipo) return false
        if (fecha != other.fecha) return false
        if (latitud != other.latitud) return false
        if (longitud != other.longitud) return false
        if (imgID != other.imgID) return false
        if (fav != other.fav) return false
        if (valoracion != other.valoracion) return false
        if (usuarioID != other.usuarioID) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nombre.hashCode()
        result = 31 * result + tipo.hashCode()
        result = 31 * result + fecha.hashCode()
        result = 31 * result + latitud.hashCode()
        result = 31 * result + longitud.hashCode()
        result = 31 * result + imgID.hashCode()
        result = 31 * result + fav.hashCode()
        result = 31 * result + valoracion
        result = 31 * result + usuarioID.hashCode()
        return result
    }

}