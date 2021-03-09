package com.andresivan.turistadroid.entidades.tiempo

object ElTiempoAPI {
    private const val API_URL = "http://api.openweathermap.org/"
    val API_KEY: String = "a575c6b751d35bfeb75ac9d52c3160ed"
    val UNITS: String = "metric"
    val LANG: String = "es"
    val service: ElTiempoREST
        get() = ElTiempoClient.getClient(API_URL)!!.create(ElTiempoREST::class.java)
}