package com.example.appclubdeportivo.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.appclubdeportivo.view_entities.Activity
import com.example.appclubdeportivo.view_entities.Customer
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ExpirationReportScreen(navController: NavController) {
    val customers = listOf(
        Customer("1", "Pepito", activities = listOf(Activity(1, "Natación")), expiredDate = "2023-12-31", amount = "2000"),
        Customer("2", "Pepe", activities = listOf(Activity(1, "Funcional")), expiredDate = "2024-02-21", amount = "4000"),
        Customer("3", "Pepin", activities = listOf(Activity(1, "Natación")), expiredDate = "2024-08-12", amount = "123"),
        Customer("4", "John", activities = listOf(Activity(1, "Yoga")), expiredDate = "2023-11-15", amount = "1000")
    )

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = Date()
    val expiredCustomers = customers.filter {
        dateFormat.parse(it.expiredDate)?.before(currentDate) == true
    }
    val reportData = expiredCustomers.map {
        listOf(it.id, it.name, it.expiredDate)
    }
    val headers = listOf("N°", "Nombre", "Fecha de Vencimiento")

    GenericReport(
        navController = navController,
        title = "Vencimientos Reporte",
        listTitle = "Listado vencimientos",
        data = reportData,
        headers = headers
    )
}
