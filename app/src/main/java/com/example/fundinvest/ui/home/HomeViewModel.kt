package com.example.fundinvest.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fundinvest.data.StatementHistoryElement
import com.example.fundinvest.repository.InvestRepository

class HomeViewModel(application:Application) : AndroidViewModel(application) {
    var historyList = MutableLiveData<List<StatementHistoryElement>>()
    private var rep: InvestRepository = InvestRepository()
    fun getData() {
        rep.getData { data ->
            historyList.value = data
        }
    }
}