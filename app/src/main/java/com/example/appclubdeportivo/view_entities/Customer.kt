package com.example.appclubdeportivo.view_entities

import com.example.appclubdeportivo.view_entities.Activity

data class Customer(val id: String, val name: String, val activities: List<Activity> = emptyList(), val expiredDate: String, val amount: String) {
}