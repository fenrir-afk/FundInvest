package com.example.fundinvest.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundinvest.data.StatementHistoryElement
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDate

class HomeViewModel : ViewModel() {
    var historyList = MutableLiveData<List<StatementHistoryElement>>()

   fun getData() {
       var localHistoryList = mutableListOf<StatementHistoryElement>()
       val userId = FirebaseAuth.getInstance().currentUser!!.uid
       val databaseRef = FirebaseDatabase.getInstance().reference
           .child("Users")
           .child(userId)
           .child("StatementsHistory")
       databaseRef.addValueEventListener(object : ValueEventListener {
           override fun onDataChange(snapshot: DataSnapshot) {
               for (userSnapshot in snapshot.children) {
                   val history = userSnapshot.getValue(StatementHistoryElement::class.java)
                   localHistoryList.add(StatementHistoryElement(history!!.date,history.token))
               }
               historyList.postValue(localHistoryList)
           }

           override fun onCancelled(error: DatabaseError) {
               println("Failed to read data: $error")
           }
       })

   }
}