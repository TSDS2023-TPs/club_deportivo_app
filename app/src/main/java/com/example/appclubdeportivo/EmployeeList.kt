package com.example.appclubdeportivo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.GenericCard
import com.example.appclubdeportivo.ui.theme.PersonalizedText
import com.example.appclubdeportivo.ui.theme.SelectableButton

@Composable
fun EmployeeListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var selectedButton by remember { mutableStateOf("Lista") }

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
                    com.example.appclubdeportivo.ui.theme.SearchBar(searchText) { searchText = it }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SelectableButton("Lista", selectedButton == "Lista") { selectedButton = "Lista" }
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
                    val exampleEmployees = listOf(Employee("1234", "Dario", "Musculacion", 4500.0),
                        Employee("1235", "Juan", "Nutrición", 5000.0))

                    for (employee in exampleEmployees) {
                        GenericCard(
                            field1 = PersonalizedText(employee.employeeId),
                            field2 = PersonalizedText(employee.name),
                            field3 = PersonalizedText(employee.specialty),
                            field4 = PersonalizedText(employee.salary.toString(), backgroundColor = Color.White),
                            field5 = PersonalizedText("Especialidad"),
                            field6 = PersonalizedText("Valor Hora"),
                            onEditClick = { /* a implementar */ }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
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
