package com.example.fundinvest.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fundinvest.data.StatementHistoryElement
import com.example.fundinvest.repository.InvestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application:Application) : AndroidViewModel(application) {
    var historyList = MutableLiveData<List<StatementHistoryElement>>()
    private var rep: InvestRepository = InvestRepository()
    fun getData() {
        viewModelScope.launch(Dispatchers.IO){
            rep.getData { data ->
                historyList.postValue(data)
            }
        }
    }
}