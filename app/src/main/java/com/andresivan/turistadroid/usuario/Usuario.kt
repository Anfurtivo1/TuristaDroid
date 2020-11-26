package com.andresivan.turistadroid.usuario

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Usuario(
    @PrimaryKey var id: Long = 0,
    var nombre: String = "",
    var login: String = "",
    var contrasena: String = "",
    var fotoUsuario: String = "",
    var correo: String = "",
    var cuentaTwitter: String = ""
) : RealmObject() {

    constructor(
        nombre: String,
        login: String,
        contrasena: String,
        fotoUsuario: String,
        correo: String,
        cuentaTwitter: String
    ): this((System.currentTimeMillis() / 1000L), nombre, login, contrasena, fotoUsuario, correo, cuentaTwitter)

    override fun toString(): String {
        return "Usuario(id=$id, nombre= '$nombre', login= '$login', contrasena= '$contrasena', foto= '$fotoUsuario', e-mail= '$correo', twitter= '$cuentaTwitter')"
    }
}