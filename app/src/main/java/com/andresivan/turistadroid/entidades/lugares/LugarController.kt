package com.andresivan.turistadroid.entidades.lugares

import io.realm.Realm
import io.realm.kotlin.where

object LugarController {

    /**
     * Función que se encarga de recoger todos los registros de la BBDD
     * @return MutableList<Lugar>? es la lista que nos devuelve, la utilizaremos para cargar los
     * CardView con su información
     */
    fun selectAll(): MutableList<Lugar>? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Lugar>().findAll()
        )
    }
    /**
     * Función que se encarga de insertar un lugar en la BBDD
     * @param lugar Lugar es el objeto que queremos insertar en la base datos
     */
    fun insert(lugar: Lugar) {
        Realm.getDefaultInstance().executeTransaction {
            it.copyToRealm(lugar); // Copia, inserta
        }
    }

    /**
     * Función que permite borrar un registro de la base de datos
     * @param lugar Lugar es el objeto o registro que queremos borrar de la BBDD
     */
    fun delete(lugar: Lugar) {
        Realm.getDefaultInstance().executeTransaction {
            it.where<Lugar>().equalTo("id", lugar.id).findFirst()?.deleteFromRealm()
        }
    }

    /**
     * Función que nos permite modificar un registro, dado el objeto que queremos modificar
     * @param lugar Lugar es el objeto que queremos modificar
     */
    fun update(lugar: Lugar) {
        Realm.getDefaultInstance().executeTransaction {
            it.copyToRealmOrUpdate(lugar)
        }
    }

    /**
     * Función que permite buscar un Lugar dado su nombre
     * @param nombre String es el nombre del lugar que nos permite buscar un lugar o no
     * @return Lugar? puede que devuelva un lun lugar o no dependiendo de si existe o no
     */
    fun selectByNombre(nombre: String): Lugar? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Lugar>().equalTo("nombre", nombre).findFirst()
        )
    }

    /**
     * Función que devuelve un registro de un lugar dado su ID
     * @param id String es el identificador del lugar
     * @return Lugar? puede que devuelva un lugar o no dependiendo de si existe o no
     */
    fun selectById(id: String): Lugar? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Lugar>().equalTo("id", id).findFirst()
        )
    }

    /**
     * Función que nos permite eliminar todos los registros de la tabla Lugar
     */
    fun removeAll() {
        Realm.getDefaultInstance().executeTransaction {
            Realm.getDefaultInstance().where<Lugar>().findAll().deleteAllFromRealm();
        }
    }

}