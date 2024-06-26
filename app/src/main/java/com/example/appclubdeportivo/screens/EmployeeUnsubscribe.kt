package com.example.appclubdeportivo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.SelectableButton
import com.example.appclubdeportivo.view_entities.Employee

@Composable
fun EmployeeUnsubscribeScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var selectedButton by remember { mutableStateOf("Baja") }
    var employees by remember { mutableStateOf(listOf(
        Employee("1234", "Dario", "Musculacion", 4500.0),
        Employee("1235", "Juan", "Nutrición", 5000.0)
    )) }
    var selectedEmployees by remember { mutableStateOf(setOf<String>()) }

    AppClubDeportivoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                Header(
                    title = "ABM Empleado",
                    showBackButton = true,
                    colorText = MaterialTheme.colorScheme.onSecondary,
                    backgroundColor = MaterialTheme.colorScheme.tertiary,
                    onBackButtonClick = { navController.popBackStack() }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    com.example.appclubdeportivo.ui.theme.SearchBar(
                        searchText
                    ) { searchText = it }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SelectableButton("Lista", selectedButton == "Lista") {
                            selectedButton = "Lista"
                            navController.navigate("employee_list")
                        }
                        SelectableButton("Alta", selectedButton == "Alta") {
                            selectedButton = "Alta"
                            navController.navigate("employee_register")
                        }
                        SelectableButton("Baja", selectedButton == "Baja") {
                            selectedButton = "Baja"
                            navController.navigate("employee_unsubscribe")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    employees.forEach { employee ->
                        EmployeeRow(
                            employee = employee,
                            isChecked = selectedEmployees.contains(employee.employeeId),
                            onCheckedChange = { isChecked ->
                                selectedEmployees = if (isChecked) {
                                    selectedEmployees + employee.employeeId
                                } else {
                                    selectedEmployees - employee.employeeId
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            employees = employees.filterNot { selectedEmployees.contains(it.employeeId) }
                            selectedEmployees = emptySet()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Confirmar")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Cerrar Sesión",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.navigate("login")
                        }
                    )
                }
            }
        }
    }
}
