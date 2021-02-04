package com.andresivan.turistadroid.entidades.fotos.servicios

import com.andresivan.turistadroid.entidades.fotos.servicios.FotoClient
import com.andresivan.turistadroid.entidades.fotos.servicios.FotoREST

object FotoAPI {
    private const val ipServer ="192.168.1.49"

    private const val puertoServer ="6969"

    private const val urlAPI ="http://$ipServer:$puertoServer/"

    val servicio: FotoREST get() = FotoClient.getClient(urlAPI)!!.create(FotoREST::class.java)
}