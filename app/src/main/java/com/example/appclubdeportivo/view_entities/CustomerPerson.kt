package com.example.appclubdeportivo.view_entities

data class CustomerPerson(
    val customerId: Int,
    val personId: Int,
    val firstName: String,
    val lastName: String,
    val documentType: Int,
    val documentId: String,
    val birthDate: String,
    val telephone: String,
    val gender: String,
    val weight: Float,
    val height: Float,
    val physicalCheck: Boolean,
    val membershipType: String
    )
