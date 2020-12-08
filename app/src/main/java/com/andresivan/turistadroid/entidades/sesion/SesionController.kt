package com.andresivan.turistadroid.entidades.sesion

import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.entidades.usuario.Usuario
import io.realm.Realm
import io.realm.kotlin.where

object SesionController {

    /**
     * Función que se encarga de insertar un usuario en la tabla de Sesión, esto nos valdrá para
     * usar ese usuario en la base de datos
     * @param sesion Sesion le introducimos lo necesario para iniciar sesion el usuario
     */
    fun insert (sesion: Sesion) {
        Realm.getDefaultInstance().executeTransaction {
            it.copyToRealm(sesion)
        }
    }

    /**
     * Función que se encarga de obtener todos los 
     */
    fun selectAll(): MutableList<Sesion>? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Sesion>().findAll()
        )
    }

    /**
     * Función que se encarga de obtener el registro de una sesión de un usuario
     */
    fun selectByIdUsuarioActivo(idUsuarioActivo: String): Sesion? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Sesion>().equalTo("idUsuarioActivo", idUsuarioActivo).findFirst()
        )
    }

    /**
     * Función que nos permite borrar todas las sesiones que haya en la base de datos salvo la que
     * vayamos a usar
     */
    fun deleteSesion(idUsuarioActivo: String){
        var realm = Realm.getDefaultInstance()
        val result = realm.where<Sesion>().notEqualTo("idUsuarioActivo", idUsuarioActivo).findAll()

        realm.executeTransaction{ realm ->
            result.deleteAllFromRealm()
        }
    }

    fun removeAll() {
        Realm.getDefaultInstance().executeTransaction {
            it.deleteAll()
        }
    }

}