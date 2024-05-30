package com.example.fundinvest.ui.ipo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundinvest.data.IPOData
import com.example.fundinvest.repository.InvestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IpoViewModel: ViewModel() {
   var ipoList: MutableLiveData<List<IPOData>> = MutableLiveData()
    private var repository: InvestRepository = InvestRepository()
    fun getIpoCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getIpoCompanies {
                ipoList.postValue(it)
            }
        }
    }
}