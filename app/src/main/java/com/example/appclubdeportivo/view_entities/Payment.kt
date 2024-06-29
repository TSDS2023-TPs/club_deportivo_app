package com.example.appclubdeportivo.view_entities


data class Payment(
    val customerId: String,
    val monthYear: String,
    val dueDate: String,
    val paymentDate: String? = null,
    val amount: String,
    val feeId: Int,
    val paymentMethod: String
)
