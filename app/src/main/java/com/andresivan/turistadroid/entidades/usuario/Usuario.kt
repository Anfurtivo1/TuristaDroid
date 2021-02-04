package com.andresivan.turistadroid.entidades.usuario

import java.util.*

open class Usuario(
    var id: String = "",
    var correo: String = "",
    var contrasena: String = "",
    var nombre: String = "",
    var fotoUsuario: String = "",
    var cuentaTwitter: String = "https://twitter.com/ICoelloC"
){
    constructor(
        correo: String,
        contrasena: String,
        nombre: String,
        fotoUsuario: String,
        cuentaTwitter: String
    ) : this((UUID.randomUUID().toString()), correo, contrasena, nombre, fotoUsuario, cuentaTwitter)

    override fun toString(): String {
        return "Usuario [id=$id, e-mail= '$correo', contrasena= '$contrasena', nombre= '$nombre', foto= '$fotoUsuario', twitter= '$cuentaTwitter']"
    }
}