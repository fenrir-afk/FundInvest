package com.example.fundinvest.ui.statements

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundinvest.data.BalanceSheet
import com.example.fundinvest.data.BalanceSheetStatementsData
import com.example.fundinvest.data.CashFlow
import com.example.fundinvest.data.CashFlowStatementsData
import com.example.fundinvest.data.IncomeStatement
import com.example.fundinvest.data.IncomeStatementsData
import com.example.fundinvest.retrofit.AlphaVantageApi
import com.example.fundinvest.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementsViewModel : ViewModel() {
    var incomeStatements:MutableLiveData<List<IncomeStatement>> = MutableLiveData()
    var balanceSheetStatements:MutableLiveData<List<BalanceSheet>> = MutableLiveData()
    var cashFlowStatements:MutableLiveData<List<CashFlow>> = MutableLiveData()
    companion object{
        const val API_KEY = "UAMCXPM77HIW9QEY"
    }

   fun getIncomeStatement(token:String = ""){
       val alphaVantageApi = RetrofitHelper.getInstance().create(AlphaVantageApi::class.java)
       // launching a new coroutine
       viewModelScope.launch(Dispatchers.IO) {
           alphaVantageApi.getIncomeStatement(symbol =  token, apikey = API_KEY)
               .enqueue(object : Callback<IncomeStatementsData> {
                   override fun onResponse(call: Call<IncomeStatementsData>, response: Response<IncomeStatementsData>) {
                       if (response.isSuccessful) {
                           val incomeResponse = response.body()
                           incomeStatements.postValue(incomeResponse?.annualReports ?: emptyList())
                           Log.d("Income statement","Api data was Successfully get")
                       } else {
                           Log.d("Income statement","Response is not successful")
                       }
                   }

                   override fun onFailure(call: Call<IncomeStatementsData>, t: Throwable) {
                       Log.d("Income statement","Fail")
                   }
               })

       }
   }
    fun getBalanceSheet(token:String = ""){
        val alphaVantageApi = RetrofitHelper.getInstance().create(AlphaVantageApi::class.java)
        // launching a new coroutine
        viewModelScope.launch(Dispatchers.IO) {
            alphaVantageApi.getBalanceSheetStatement(symbol =  token, apikey = API_KEY)
                .enqueue(object : Callback<BalanceSheetStatementsData> {
                    override fun onResponse(call: Call<BalanceSheetStatementsData>, response: Response<BalanceSheetStatementsData>) {
                        if (response.isSuccessful) {
                            val balanceSheetResponse = response.body()
                            balanceSheetStatements.postValue(balanceSheetResponse?.annualReports ?: emptyList())
                            Log.d("Balance sheet","Api data was Successfully get")
                        } else {
                            Log.d("Balance sheet","Response is not successful")
                        }
                    }

                    override fun onFailure(call: Call<BalanceSheetStatementsData>, t: Throwable) {
                        Log.d("Balance sheet","Fail")
                    }
                })
        }
    }
    fun getCashFlow(token:String = ""){
        val alphaVantageApi = RetrofitHelper.getInstance().create(AlphaVantageApi::class.java)
        // launching a new coroutine
        viewModelScope.launch(Dispatchers.IO) {
            alphaVantageApi.getCashFlowStatement(symbol =  token, apikey = API_KEY)
                .enqueue(object : Callback<CashFlowStatementsData> {
                    override fun onResponse(call: Call<CashFlowStatementsData>, response: Response<CashFlowStatementsData>) {
                        if (response.isSuccessful) {
                            val cashFlowResponse = response.body()
                            cashFlowStatements.postValue(cashFlowResponse?.annualReports ?: emptyList())
                            Log.d("Cash flow statement","Api data was Successfully get")
                        } else {
                            Log.d("Cash flow statement","Response is not successful")
                        }
                    }

                    override fun onFailure(call: Call<CashFlowStatementsData>, t: Throwable) {
                        Log.d("Cash flow statement","Fail")
                    }
                })
        }
    }
}