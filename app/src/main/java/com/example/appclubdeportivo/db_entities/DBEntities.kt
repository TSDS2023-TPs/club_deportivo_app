package com.example.appclubdeportivo.db_entities

data class Role(
    val roleId: Int,
    val roleDesc: String
)

data class User(
    val userId: Int,
    val userName: String,
    val userPass: String,
    val roleId: Int,
    val active: Boolean = true
)

data class DocumentType(
    val documentTypeId: Int,
    val documentTypeDesc: String
)

data class Person(
    val personId: Int,
    val identityDocument: String,
    val documentTypeId: Int,
    val firstName: String,
    val lastName: String,
    val address: String,
    val birthDate: String,
    val gender: String,
    val weight: Double
)

data class Employee(
    val employeeId: Int,
    val userId: Int,
    val personId: Int,
    val startHour: Int,
    val endHour: Int,
    val daysOfWeek: Int,
    val hourlyRate: Int,
    val hireDate: String,
    val terminationDate: String
)

data class Specialty(
    val specialtyId: Int,
    val specialtyDesc: String
)

data class Professor(
    val professorId: Int,
    val employeeId: Int,
    val specialtyId: Int,
    val observation: String
)

data class Doctor(
    val doctorId: Int,
    val employeeId: Int,
    val specialtyId: Int,
    val observation: String
)

data class AccountStatus(
    val accountStatusId: Int,
    val accountStatusDesc: String,
    val accountStatusDetail: String
)

data class Activity(
    val activityId: Int,
    val activityType: String,
    val day: String?,
    val hour: Int?,
    val teacherId: Int,
    val classFee: Int
)

data class Customer(
    val customerId: Int,
    val personId: Int,
    val membershipType: String,
    val activityId: Int,
    val accountStatusId: Int,
    val fit: Boolean,
    val startDate: String,
    val endDate: String
)

data class Class(
    val classId: Int,
    val activityId: Int,
    val dateTime: String
)

data class Attendance(
    val classId: Int,
    val customerId: Int
)

data class Routine(
    val routineId: Int,
    val customerId: Int,
    val activityId: Int,
    val day: String,
    val detail: String,
    val teacherId: Int,
    val expirationDate: String
)

data class PaymentMethod(
    val paymentMethodId: Int,
    val paymentMethodDesc: String,
    val appliesPromotion: Boolean
)

data class Fee(
    val feeId: Int,
    val customerId: Int,
    val amount: Int,
    val month: Int,
    val dueDate: String,
    val status: String
)

data class Invoice(
    val invoiceId: Int,
    val customerId: Int,
    val amount: Float,
    val date: String,
    val feeId: Int?,
    val paymentMethodId: Int?
)
