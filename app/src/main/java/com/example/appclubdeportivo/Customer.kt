package com.example.appclubdeportivo

data class Customer(val id: String, val name: String, val activities: List<Activity> = emptyList(), val expiredDate: String, val amount: String) {
}