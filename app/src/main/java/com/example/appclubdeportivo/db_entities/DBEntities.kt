package com.example.appclubdeportivo.db_entities

import androidx.room.*
import com.example.appclubdeportivo.view_entities.Activity

@Entity
data class Role(
    @PrimaryKey(autoGenerate = true) val roleId: Int = 0,
    val description: String
)

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val username: String,
    val password: String,
    val roleId: Int,
    val isActive: Boolean = true
)

@Entity
data class DocumentType(
    @PrimaryKey(autoGenerate = true) val documentTypeId: Int = 0,
    val description: String
)

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val personId: Int = 0,
    val identityDocumentNumber: String,
    val documentTypeId: Int,
    val firstName: String,
    val lastName: String,
    val address: String,
    val birthDate: String,
    val gender: String,
    val weightKg: Double? = null
)

@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true) val employeeId: Int = 0,
    val userId: Int,
    val personId: Int,
    val startHour: Int,
    val endHour: Int,
    val daysOfWeek: String,
    val hourlyRate: Int,
    val hireDate: String,
    val endDate: String?
)

@Entity
data class Specialty(
    @PrimaryKey(autoGenerate = true) val specialtyId: Int = 0,
    val description: String
)

@Entity
data class Professor(
    @PrimaryKey(autoGenerate = true) val professorId: Int = 0,
    val employeeId: Int,
    val specialtyId: Int,
    val notes: String
)

@Entity
data class Doctor(
    @PrimaryKey(autoGenerate = true) val doctorId: Int = 0,
    val employeeId: Int,
    val specialtyId: Int,
    val notes: String
)

@Entity
data class AccountStatus(
    @PrimaryKey(autoGenerate = true) val accountStatusId: Int = 0,
    val description: String,
    val details: String
)

@Entity
data class Activity(
    @PrimaryKey(autoGenerate = true) val activityId: Int = 0,
    val type: String,
    val dayOfWeek: String?,
    val hourOfDay: Int?,
    val employeeId: Int,
    val classFee: Float
)

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true) val customerId: Int = 0,
    val personId: Int,
    val membershipType: String,
    val accountStatusId: Int,
    val hasPhysicalCheck: Boolean,
    val startDate: String,
    val endDate: String? = null
)

@Entity
data class Class(
    @PrimaryKey(autoGenerate = true) val classId: Int = 0,
    val activityId: Int,
    val dateTime: String
)

@Entity(
    primaryKeys = ["classId", "customerId"]
)
data class Attendance(
    val classId: Int,
    val customerId: Int
)

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true) val routineId: Int = 0,
    val customerActivityId: Int,
    val dayOfWeek: String?,
    val details: String,
    val professorId: Int,
    val expirationDate: String?
)

@Entity
data class PaymentMethod(
    @PrimaryKey(autoGenerate = true) val paymentMethodId: Int = 0,
    val description: String,
    val hasPromotion: Boolean
)

@Entity
data class Fee(
    @PrimaryKey(autoGenerate = true) val feeId: Int = 0,
    val customerId: Int,
    val amount: Int,
    val month: Int,
    val dueDate: String,
    val status: String
)

@Entity
data class Invoice(
    @PrimaryKey(autoGenerate = true) val invoiceId: Int = 0,
    val customerId: Int,
    val amount: Float,
    val date: String,
    val feeId: Int,
    val paymentMethodId: Int
)
@Entity
data class CustomerActivity(
    @PrimaryKey(autoGenerate = true) val customerActivityId: Int = 0,
    val customerId: Int,
    val activityId: Int
)
