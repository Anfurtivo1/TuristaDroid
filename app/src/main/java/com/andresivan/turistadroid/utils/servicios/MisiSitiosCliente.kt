package com.andresivan.turistadroid.utils.servicios

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MisiSitiosCliente {
    private var retrofit: Retrofit? = null
    fun getCliente(url: String): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}