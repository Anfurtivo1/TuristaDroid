package com.andresivan.turistadroid.entidades.lugares

import io.realm.Realm
import io.realm.kotlin.where

object LugarController {


    /**
     * Función que nos permite obtener todos los lugares almacenados en la base de datos
     * @return MutableList<Lugar>? Una lista que almacena todos los objetos del tipo Lugar, el
     * signo de interrogación se debe a que puede que no nos devuelva nada si no hay ningún lugar en la
     * base de datos
     */
    fun selectAll(): MutableList<Lugar>? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Lugar>().findAll()
        )
    }

    /**
     * Función que nos permite añadir nuevos lugares a la base de datos
     * @param lugar Lugar es un nuevo lugar que añadimos a la base de datos
     */
    fun insert(lugar: Lugar) {
        Realm.getDefaultInstance().executeTransaction {
            it.copyToRealm(lugar)
        }
    }

    /**
     * Función que nos permite borrar un lugar de la base de datos
     * @param lugar Lugar es el lugar que queremos borrar de la base de datos
     */
    fun delete(lugar: Lugar) {
        Realm.getDefaultInstance().executeTransaction {
            it.where<Lugar>().equalTo("id", lugar.id).findFirst()?.deleteFromRealm()
        }
    }

    /**
     * Función que nos permite modificar un lugar de la base de datos
     * @para lugar Lugar que queremos modificar.
     */
    fun update(lugar: Lugar) {
        Realm.getDefaultInstance().executeTransaction {
            it.copyToRealmOrUpdate(lugar)
        }
    }

    /**
     * Función que nos permite buscar un lugar por su nombre
     * @return Lugar? esta función nos permite coger un lugar de la base de datos la interrogación significa
     * que puede que no devuelva ningún lugar con ese nombre
     */
    fun selectByNombre(nombre: String): Lugar? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Lugar>().equalTo("nombre", nombre).findFirst()
        )
    }

    /**
     * Función que nos permite buscar un lugar por su identificador
     * @param id String este parámetro es el identificador para localizar es lugar concreto en la base de datos
     * @return Usuario? puede que devuelva un lugar o no
     */
    fun selectById(id: String): Lugar? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Lugar>().equalTo("id", id).findFirst()
        )
    }

    /**
     * Función que elimina todos los registros
     * no recibe parámetro
     */
    fun removeAll() {
        Realm.getDefaultInstance().executeTransaction {
            Realm.getDefaultInstance().where<Lugar>().findAll().deleteAllFromRealm()
        }
    }
}