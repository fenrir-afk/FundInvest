package com.example.fundinvest.ui.registry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundinvest.repository.InvestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistryViewModel:ViewModel() {
    private var rep: InvestRepository = InvestRepository()
    var regResult = MutableLiveData<Boolean>()
    fun registry(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            rep.registry(email,password){
                regResult.postValue(it)
            }
        }

    }
}