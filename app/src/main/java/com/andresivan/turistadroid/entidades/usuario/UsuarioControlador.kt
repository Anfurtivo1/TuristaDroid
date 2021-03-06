package com.andresivan.turistadroid.usuario

import android.util.Log
import android.widget.Toast
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.utils.CifradorContrasena
import io.realm.Realm

import io.realm.Realm.*
import io.realm.kotlin.where

object UsuarioControlador {


    /**
     * Función para insertar un nuevo usuario en la Base de datos de Realm
     * @param usuario Un objeto del tipo Usuario, con su id, nombre, etc...
     */
    fun insert(usuario: Usuario) {
        getDefaultInstance().executeTransaction {
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
    fun update(usuario: Usuario) {
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
     * Función que se encarga de comprobar si existe algún usuario en la aplicación con ese correo,
     * dependiendo del valor boolean que devuelva hará una cosa u otra
     * @param correo String es el correo del usuario que se quiere registrar, la contraseña y el
     * nombre de usuario pueden repetirse pero el correo no
     */
    fun existeUsuario(correo: String, contrasena: String): Boolean {
        //var usuarioExiste: Boolean = false
        val realm = Realm.getDefaultInstance()
        var query = realm.where<Usuario>().equalTo("correo", correo)
            .equalTo("contrasena", CifradorContrasena.convertirHash(contrasena, "SHA-256"))
            .findAll()
        return query.count() > 0
    }

    /**
     * Función que nos permite buscar un usuario en nuestra base de datos, está función es igual que la anterior, pero
     * esta vez, buscamos por el id del usuario
     * @param id String
     * @return usuario Puede que devuelva algún usuario o no por eso en el tipo de valor que devuelve ponemos Usuario?
     */
    fun selectById(id: String): Usuario? {
        return getDefaultInstance().copyFromRealm(
            getDefaultInstance().where<Usuario>().equalTo("id", id).findFirst()
        )
    }


    /**
     * Función que nos permite actualizar un registro de la BBDD de Usuarios
     * @param usuario Usuario que queremos modificar
     */
    fun updateUsuario(usuario: Usuario, id: String) {
        var realm = Realm.getDefaultInstance()

        realm.where<Usuario>().equalTo("id",id)

        realm.executeTransaction { realm ->
            realm.insertOrUpdate(usuario)
        }
        realm.commitTransaction()
    }

}