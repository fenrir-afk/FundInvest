package com.example.fundinvest.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundinvest.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {

   fun getData() {
       val rootRef = FirebaseDatabase.getInstance().reference
       val ordersRef = rootRef.child("Users").addValueEventListener(object : ValueEventListener {
           override fun onDataChange(snapshot: DataSnapshot) {
               // Обработка полученных данных
               for (userSnapshot in snapshot.children) {
                   val user = userSnapshot.getValue(User::class.java)
                   // Используйте полученные данные пользователя
                   println("User email: ${user?.email}")
                   println("User password: ${user?.password}")
               }
           }

           override fun onCancelled(error: DatabaseError) {
               // Обработка ошибок
               println("Failed to read data: $error")
           }
       })

   }
}