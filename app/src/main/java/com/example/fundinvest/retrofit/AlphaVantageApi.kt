package com.example.fundinvest.retrofit

import com.example.fundinvest.data.BalanceSheetStatementsData
import com.example.fundinvest.data.CashFlowStatementsData
import com.example.fundinvest.data.IncomeStatementsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageApi {
    @GET("query")
    fun getIncomeStatement(
        @Query("function") function: String = "income_statement",
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String
    ): Call<IncomeStatementsData>
    @GET("query")
    fun getBalanceSheetStatement(
        @Query("function") function: String = "BALANCE_SHEET",
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String
    ): Call<BalanceSheetStatementsData>
    @GET("query")
    fun getCashFlowStatement(
        @Query("function") function: String = "CASH_FLOW",
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String
    ): Call<CashFlowStatementsData>
}