package com.example.fundinvest.data

data class StatementHistoryElement(
    val date:String,
    val token:String
){
    // Конструктор по умолчанию
    constructor() : this("", "")
}