package com.andresivan.turistadroid.entidades.usuario.servicios

import com.andresivan.turistadroid.entidades.fotos.servicios.FotoClient
import com.andresivan.turistadroid.entidades.fotos.servicios.FotoREST

object UsuarioAPI {
    private const val ipServer ="192.168.1.49"

    private const val puertoServer ="6969"

    private const val urlAPI ="http://$ipServer:$puertoServer/"

    val service: UsuarioREST get() = UsuarioClient.getClient(urlAPI)!!.create(UsuarioREST::class.java)
}