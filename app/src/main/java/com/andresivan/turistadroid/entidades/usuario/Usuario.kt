package com.andresivan.turistadroid.entidades.usuario

<<<<<<< HEAD
import java.util.*

open class Usuario(
    var id: String = "",
    var correo: String = "",
    var contrasena: String = "",
    var nombre: String = "",
    var fotoUsuario: String = "",
    var cuentaTwitter: String = "https://twitter.com/ICoelloC"
){
=======
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Usuario(
    @PrimaryKey var id: String = "",
    @Required var correo: String = "",
    @Required var contrasena: String = "",
    @Required var nombre: String = "",
    var fotoUsuario: String = "",
    var cuentaTwitter: String = "https://twitter.com/ICoelloC"
) : RealmObject(){

>>>>>>> main
    constructor(
        correo: String,
        contrasena: String,
        nombre: String,
        fotoUsuario: String,
        cuentaTwitter: String
    ) : this((UUID.randomUUID().toString()), correo, contrasena, nombre, fotoUsuario, cuentaTwitter)

    override fun toString(): String {
<<<<<<< HEAD
        return "Usuario [id=$id, e-mail= '$correo', contrasena= '$contrasena', nombre= '$nombre', foto= '$fotoUsuario', twitter= '$cuentaTwitter']"
=======
        return "Usuario(id=$id, e-mail= '$correo', contrasena= '$contrasena', nombre= '$nombre', foto= '$fotoUsuario', twitter= '$cuentaTwitter')"
>>>>>>> main
    }
}