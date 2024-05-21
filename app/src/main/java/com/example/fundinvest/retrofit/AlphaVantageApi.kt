package com.example.fundinvest.retrofit

import com.example.fundinvest.data.IncomeList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageApi {
    @GET("query")
    fun getIncomeStatement(
        @Query("function") function: String = "income_statement",
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String
    ): Call<IncomeList>
}