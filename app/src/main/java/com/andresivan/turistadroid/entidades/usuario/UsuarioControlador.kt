package com.andresivan.turistadroid.entidades.usuario

import io.realm.Realm.*
import io.realm.kotlin.where

object UsuarioControlador {


    /**
     * Función para insertar un nuevo usuario en la Base de datos de Realm
     * @param usuario Un objeto del tipo Usuario, con su id, nombre, etc...
     */
    fun insert(usuario: Usuario){
        getDefaultInstance().executeTransaction{
            it.copyToRealm(usuario)
        }
    }


    /**
     * Función que nos permite borrar un usuario de la base de datos
     * @param usuario Un objeto del tipo usuario, en este, usaremos su id para localizar el usuario que queremos borrar
     */
    fun delete(usuario: Usuario) {
        getDefaultInstance().executeTransaction {
            it.where<Usuario>().equalTo("id", usuario.id).findFirst()?.deleteFromRealm()
        }
    }


    /**
     * Función que nos permite modificar un usuario.
     * @param usuario el usuario que queremos actualizar
     */
    fun update(usuario: Usuario){
        getDefaultInstance().executeTransaction {
            it.copyToRealmOrUpdate(usuario)
        }
    }


    /**
     * Función que nos permite buscar un usuario en nuestra base de datos, está función la podemos usar para iniciar
     * sesión o antes de registrarnos para comprobar si ya existe algún usuario con ese valor
     * @param correo String
     * @return usuario Puede que devuelva algún usuario o no por eso en el tipo de valor que devuelve ponemos Usuario?
     */
    fun selectByCorreo(correo: String): Usuario? {
        return getDefaultInstance().copyFromRealm(
            getDefaultInstance().where<Usuario>().equalTo("correo", correo).findFirst()
        )
    }


    /**
     * Función que nos permite buscar un usuario en nuestra base de datos, está función es igual que la anterior, pero
     * esta vez, buscamos por el id del usuario
     * @param id String
     * @return usuario Puede que devuelva algún usuario o no por eso en el tipo de valor que devuelve ponemos Usuario?
     */
    fun selectById(id: Long): Usuario? {
        return getDefaultInstance().copyFromRealm(
            getDefaultInstance().where<Usuario>().equalTo("id", id).findFirst()
        )
    }


    /**
     * Función que nos permite borrar todos los usuarios de la base de datos
     * Esta función no recibe parámetro como las anteriores, debido a que no nos hace falta ya que queremos borrar
     * a todos los usuarios que existan
     */
    fun removeAll(){
        getDefaultInstance().executeTransaction{
            it.deleteAll();
        }
    }

}