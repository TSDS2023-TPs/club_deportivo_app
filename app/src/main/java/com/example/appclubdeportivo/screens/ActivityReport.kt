package com.example.appclubdeportivo.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.appclubdeportivo.view_entities.Activity

@Composable
fun ActivityReportScreen(navController: NavController) {
    val activities = listOf(
        Activity(1, "Funcional", "Lu, Mi y Vi","07:00 - 19:00"),
        Activity(1, "Nutrición", "Sáb","08:00"),
        Activity(1, "Natación", "Mar, Jue y Sáb","12:00 - 14:00"),
    )

    GenericReport(navController, "Actividades Reporte", "Listado actividades", activities.map {
        listOf(it.name, it.days, it.hours)
    }, listOf("Descripción", "Días", "Horarios"))
}

