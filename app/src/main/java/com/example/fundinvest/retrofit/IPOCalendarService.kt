package com.example.fundinvest.retrofit

import com.example.fundinvest.data.IPOData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IPOCalendarService {
    @GET("ipo_calendar")
    fun getIPOCalendar(@Query("apikey") apiKey: String): Call<List<IPOData>>
}