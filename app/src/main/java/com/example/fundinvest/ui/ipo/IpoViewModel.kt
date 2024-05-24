package com.example.fundinvest.ui.ipo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundinvest.data.IPOData
import com.example.fundinvest.data.IncomeStatementsData
import com.example.fundinvest.retrofit.AlphaVantageApi
import com.example.fundinvest.retrofit.IPOCalendarService
import com.example.fundinvest.retrofit.RetrofitHelper
import com.example.fundinvest.ui.statements.StatementsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IpoViewModel: ViewModel() {
   var ipoList: MutableLiveData<List<IPOData>> = MutableLiveData()
    companion object{
        const val API_KEY = "ZlbEk7cFYkbMfErvhkWj9xPPNI0Qlw8M"
    }
    fun getIpoCompanies() {
        val ipoApi = RetrofitHelper.getInstanceIPO().create(IPOCalendarService::class.java)
        // launching a new coroutine
        viewModelScope.launch(Dispatchers.IO) {
            ipoApi.getIPOCalendar(API_KEY)
                .enqueue(object : Callback<List<IPOData>> {
                    override fun onResponse(
                        call: Call<List<IPOData>>,
                        response: Response<List<IPOData>>
                    ) {
                        if (response.isSuccessful) {
                            val incomeResponse = response.body()
                            ipoList.postValue(incomeResponse!!)
                        } else {
                            Log.d("Retrofit","Response is not successful")
                        }
                    }
                    override fun onFailure(call: Call<List<IPOData>>, t: Throwable) {
                        println(t.message)
                    }
                })

        }
    }
}