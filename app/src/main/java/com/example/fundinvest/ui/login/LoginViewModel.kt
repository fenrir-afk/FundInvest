package com.example.fundinvest.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundinvest.repository.InvestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    private var rep:InvestRepository = InvestRepository()
    var loginResult = MutableLiveData<Boolean>()
    fun login(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            rep.login(email,password){
                loginResult.postValue(it)
            }
        }
    }
}