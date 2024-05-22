package com.example.fundinvest.data
data class User(
    val email: String = "",
    val password: String = ""
) {
    // Конструктор по умолчанию
    constructor() : this("", "")
}