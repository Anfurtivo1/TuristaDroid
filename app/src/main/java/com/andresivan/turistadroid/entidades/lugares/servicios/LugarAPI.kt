package com.andresivan.turistadroid.entidades.lugares.servicios

/**
 * En esta clase indicamos los valores para conectarnos a la API REST y llamamos a la clase para conectarnos con esos datos
 */
object LugarAPI {
    private const val ipServer ="192.168.1.49"

    private const val puertoServer ="6969"

    private const val urlAPI ="http://$ipServer:$puertoServer/"

    val servicio:LugarREST get() = LugarClient.getClient(urlAPI)!!.create(LugarREST::class.java)

}