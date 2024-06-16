package com.example.appclubdeportivo


data class Payment(
    val customerId: String,
    val monthYear: String,
    val dueDate: String,
    val paymentDate: String,
    val amount: String
)
