package com.example.appclubdeportivo.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.appclubdeportivo.view_entities.Activity
import com.example.appclubdeportivo.view_entities.CustomerReport

@Composable
fun CustomerReportScreen(navController: NavController) {
    val customers = listOf(
        CustomerReport("1", "Pepito", activities = listOf(Activity(1, "Natación")), expiredDate = "2024-12-31"),
        CustomerReport("2", "Pepe", activities = listOf(Activity(1, "Funcional")), expiredDate = "2024-02-21"),
        CustomerReport("3", "Pepin", activities = listOf(Activity(1, "Natación")), expiredDate = "2024-08-12")
        )

    GenericReport(navController, "Clientes Reporte", "Listado clientes", customers.map {
        listOf(it.id, it.name, it.activities.joinToString { activity -> activity.name })
    }, listOf("N°", "Nombre", "Actividad"))
}