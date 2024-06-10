package com.example.fundinvest.repository

import android.util.Log
import com.example.fundinvest.data.BalanceSheet
import com.example.fundinvest.data.BalanceSheetStatementsData
import com.example.fundinvest.data.CashFlow
import com.example.fundinvest.data.CashFlowStatementsData
import com.example.fundinvest.data.IPOData
import com.example.fundinvest.data.IncomeStatement
import com.example.fundinvest.data.IncomeStatementsData
import com.example.fundinvest.data.StatementHistoryElement
import com.example.fundinvest.retrofit.AlphaVantageApi
import com.example.fundinvest.retrofit.IPOCalendarService
import com.example.fundinvest.retrofit.RetrofitHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.UUID

class InvestRepository {
    companion object{
        const val API_KEY = "3CTJ5KY53JSWIOSI" //statements
        const val API_IPO_KEY = "ZlbEk7cFYkbMfErvhkWj9xPPNI0Qlw8M"
    }
    fun getData(callback: (List<StatementHistoryElement>) -> Unit) {
        val localHistoryList = mutableListOf<StatementHistoryElement>()
        if(FirebaseAuth.getInstance().currentUser != null){ // нужно для тестов
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
            val databaseRef = FirebaseDatabase.getInstance().reference
                .child(userId)
                .child("StatementsHistory")
            databaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (userSnapshot in snapshot.children) {
                        val history = userSnapshot.getValue(StatementHistoryElement::class.java)
                        localHistoryList.add(StatementHistoryElement(history!!.date, history.token))
                    }
                    callback(localHistoryList)
                }

                override fun onCancelled(error: DatabaseError) {
                    println("Failed to read data: $error")
                }
            })
        }else{
            callback(emptyList())
        }
    }
    fun sendDataToDb(token:String){
        val userInfo = hashMapOf<String,String>()
        if (FirebaseAuth.getInstance().currentUser != null){
            val userId = FirebaseAuth.getInstance().currentUser!!.uid
            userInfo["token"] = token
            userInfo["date"] = LocalDate.now().toString()
            FirebaseDatabase.getInstance().getReference().child(userId).child("StatementsHistory").child(
                UUID.randomUUID().toString())
                .setValue(userInfo)
        }
    }
    fun getCashFlow(token:String,callback: (List<CashFlow>) -> Unit){
        val alphaVantageApi = RetrofitHelper.getInstance().create(AlphaVantageApi::class.java)
        // launching a new coroutine
        alphaVantageApi.getCashFlowStatement(symbol =  token, apikey = API_KEY)
            .enqueue(object : Callback<CashFlowStatementsData> {
                override fun onResponse(call: Call<CashFlowStatementsData>, response: Response<CashFlowStatementsData>) {
                    if (response.isSuccessful) {
                        val cashFlowResponse = response.body()
                        callback(cashFlowResponse?.annualReports ?: emptyList())
                        Log.d("Retrofit","Api data was Successfully get")
                    } else {
                        Log.d("Retrofit","Response is not successful")
                    }
                }

                override fun onFailure(call: Call<CashFlowStatementsData>, t: Throwable) {
                    Log.d("Retrofit","Fail")
                }
            })
    }
    fun getIncomeStatement(token:String,callback: (List<IncomeStatement>) -> Unit){
        val alphaVantageApi = RetrofitHelper.getInstance().create(AlphaVantageApi::class.java)
        // launching a new coroutine
            alphaVantageApi.getIncomeStatement(symbol =  token, apikey = API_KEY)
                .enqueue(object : Callback<IncomeStatementsData> {
                    override fun onResponse(call: Call<IncomeStatementsData>, response: Response<IncomeStatementsData>) {
                        if (response.isSuccessful) {
                            val incomeResponse = response.body()
                            callback(incomeResponse?.annualReports ?: emptyList())
                        } else {
                            Log.d("Retrofit","Response is not successful")
                        }
                    }
                    override fun onFailure(call: Call<IncomeStatementsData>, t: Throwable) {
                        Log.d("Retrofit","Fail")
                    }
                })
    }
    fun getBalanceSheet(token:String,callback: (List<BalanceSheet>) -> Unit){
        val alphaVantageApi = RetrofitHelper.getInstance().create(AlphaVantageApi::class.java)
        // launching a new coroutine
            alphaVantageApi.getBalanceSheetStatement(symbol =  token, apikey = API_KEY)
                .enqueue(object : Callback<BalanceSheetStatementsData> {
                    override fun onResponse(call: Call<BalanceSheetStatementsData>, response: Response<BalanceSheetStatementsData>) {
                        if (response.isSuccessful) {
                            val balanceSheetResponse = response.body()
                            callback(balanceSheetResponse?.annualReports ?: emptyList())
                            Log.d("Retrofit","Api data was Successfully get")
                        } else {
                            Log.d("Retrofit","Response is not successful")
                        }
                    }
                    override fun onFailure(call: Call<BalanceSheetStatementsData>, t: Throwable) {
                        Log.d("Retrofit","Fail")
                    }
                })
    }
    fun registry(email:String,password:String,callback: (Boolean) -> Unit){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    val userInfo = hashMapOf<String,String>()
                    userInfo["email"] = email
                    userInfo["password"] = password
                    FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(userInfo)
                    callback(true)
                }else{
                    callback(false)
                }
            }
    }
    fun login(email:String,password:String,callback: (Boolean) -> Unit){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email,
            password
        )
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    callback(true)
                }else{
                    callback(false)
                }
            }
            .addOnFailureListener {
                callback(false)
                Log.e("LoginError","Error: ${it.message}")
            }
    }
    fun getIpoCompanies(callback: (List<IPOData>) -> Unit){
        val ipoApi = RetrofitHelper.getInstanceIPO().create(IPOCalendarService::class.java)
        // launching a new coroutine
            ipoApi.getIPOCalendar(API_IPO_KEY)
                .enqueue(object : Callback<List<IPOData>> {
                    override fun onResponse(
                        call: Call<List<IPOData>>,
                        response: Response<List<IPOData>>
                    ) {
                        if (response.isSuccessful) {
                            val incomeResponse = response.body()
                            callback(incomeResponse!!)
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