package com.example.appclubdeportivo

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun EmployeeReportScreen(navController: NavController) {
    val employees = listOf(
        Employee("1", "Dario", "Musculación", 123.4),
        Employee("2", "Juan", "Nutrición", 12.1),
        Employee("3", "Pepe pepito", "Administrador", 123.1)
    )

    GenericReport(navController, "Empleados Reporte", "Listado empleados", employees.map {
        listOf(it.employeeId, it.name, it.specialty)
    }, listOf("N°", "Nombre", "Especialidad"))
}

