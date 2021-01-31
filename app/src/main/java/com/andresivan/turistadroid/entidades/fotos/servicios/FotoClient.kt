package com.andresivan.turistadroid.entidades.fotos.servicios

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Está será la clase que se encarga de conectarse a la API REST
 */
object FotoClient {
    private var retrofit: Retrofit? = null

    fun getClient(url: String): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}