package com.example.fundinvest.ui.statements

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundinvest.data.IncomeList
import com.example.fundinvest.data.IncomeStatement
import com.example.fundinvest.retrofit.AlphaVantageApi
import com.example.fundinvest.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementsViewModel : ViewModel() {
    var incomeStatements:MutableLiveData<List<IncomeStatement>> = MutableLiveData()
    companion object{
        const val API_KEY = "UAMCXPM77HIW9QEY"
    }

   fun getIncomeStatement(token:String = ""){
       val alphaVantageApi = RetrofitHelper.getInstance().create(AlphaVantageApi::class.java)
       // launching a new coroutine
       viewModelScope.launch(Dispatchers.IO) {
           alphaVantageApi.getIncomeStatement(symbol =  token, apikey = API_KEY)
               .enqueue(object : Callback<IncomeList> {
                   override fun onResponse(call: Call<IncomeList>, response: Response<IncomeList>) {
                       if (response.isSuccessful) {
                           val incomeStatement = response.body()
                           incomeStatements.postValue(incomeStatement?.annualReports ?: emptyList())
                           Log.d("Income statement","Api data was Successfully get")
                       } else {
                           Log.d("Income statement","Response is not successful")
                       }
                   }

                   override fun onFailure(call: Call<IncomeList>, t: Throwable) {
                       Log.d("Income statement","Fail")
                   }
               })

       }
   }
}