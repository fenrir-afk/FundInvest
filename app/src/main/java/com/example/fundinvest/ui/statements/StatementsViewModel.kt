package com.example.fundinvest.ui.statements


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundinvest.data.BalanceSheet
import com.example.fundinvest.data.CashFlow
import com.example.fundinvest.data.IncomeStatement
import com.example.fundinvest.repository.InvestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatementsViewModel : ViewModel() {
    var incomeStatements:MutableLiveData<List<IncomeStatement>> = MutableLiveData()
    var balanceSheetStatements:MutableLiveData<List<BalanceSheet>> = MutableLiveData()
    var cashFlowStatements:MutableLiveData<List<CashFlow>> = MutableLiveData()
    private var rep: InvestRepository = InvestRepository()


   fun getIncomeStatement(token:String = ""){
       viewModelScope.launch(Dispatchers.IO) {
           rep.getIncomeStatement(token) {
               incomeStatements.postValue(it)
           }
       }
   }
    fun getBalanceSheet(token:String = ""){
        viewModelScope.launch(Dispatchers.IO) {
            rep.getBalanceSheet(token) {
                balanceSheetStatements.postValue(it)
            }
        }
    }
    fun getCashFlow(token:String = ""){
        viewModelScope.launch(Dispatchers.IO) {
         rep.getCashFlow(token){
             cashFlowStatements.postValue(it)
         }
        }
    }
     fun sendDataToDb(token:String) {
         viewModelScope.launch(Dispatchers.IO) {
             rep.sendDataToDb(token)
         }
    }
}