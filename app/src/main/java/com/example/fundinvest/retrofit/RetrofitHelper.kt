package com.example.fundinvest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://www.alphavantage.co/"
    private const val BASE_URL_IPO = "https://financialmodelingprep.com/api/v3/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
    fun getInstanceIPO(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL_IPO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}