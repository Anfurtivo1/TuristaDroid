package com.andresivan.turistadroid.utils.servicios

object MisSitiosAPI {

    private const val DIR_SERVIDOR = "192.168.1.49"

    private const val PUERTO = "6969"
    private const val URL_API = "http://$DIR_SERVIDOR:$PUERTO"

    val service : TuristaREST
        get() = MisiSitiosCliente.getCliente(URL_API)!!.create(TuristaREST::class.java)

}