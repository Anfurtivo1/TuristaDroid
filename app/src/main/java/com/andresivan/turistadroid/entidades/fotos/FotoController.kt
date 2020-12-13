package com.andresivan.turistadroid.entidades.fotos

import io.realm.Realm
import io.realm.kotlin.where

object FotoController {

    /**
     * Función que nos permite añadir una fotografia a la base de datos
     * @param foto Foto le pasamos por parámatro un objeto del tipo Foto y este será borrado de la base de datos
     */
    fun insert(foto: Foto) {
        Realm.getDefaultInstance().executeTransaction {
            it.copyToRealm(foto)
        }
    }

    /**
     * Función que nos permite borrar una fotografía de la base de datos
     * @param foto Foto que le pasamos por parámetro de la cual cogeremos su id y lo utilizaremos para
     * localizarla en la base de datos
     */
    fun delete(foto: Foto) {
        Realm.getDefaultInstance().executeTransaction {
            it.where<Foto>().equalTo("id", foto.id).findFirst()?.deleteFromRealm()
        }
    }

    /**
     * Función que recibe el identificador de una fotografia y que nos permite localizarla en la base de datos
     * @param id String el identificador que utlizamos para buscar la foto
     * @return Foto
     */
    fun selectById(id: String): Foto? {
        return Realm.getDefaultInstance().copyFromRealm(
            Realm.getDefaultInstance().where<Foto>().equalTo("id", id).findFirst()
        )
    }

    fun update(foto: Foto) {
        Realm.getDefaultInstance().executeTransaction {
            it.copyToRealmOrUpdate(foto)
        }
    }
}