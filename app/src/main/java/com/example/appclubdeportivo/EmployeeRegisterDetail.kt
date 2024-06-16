package com.example.appclubdeportivo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.SelectableButton

@Composable
fun EmployeeRegisterDetailScreen(navController: NavController) {
    var selectedButton by remember { mutableStateOf("Alta") }
    var specialty by remember { mutableStateOf("") }
    var weekHours by remember { mutableStateOf("") }
    var hourRate by remember { mutableStateOf("") }
    AppClubDeportivoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                Header(
                    title = "ABM Cliente",
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SelectableButton("Lista", selectedButton == "Lista") {
                            selectedButton = "Lista"
                            navController.navigate("customer_list")
                        }
                        SelectableButton("Alta", selectedButton == "Alta") {
                            selectedButton = "Alta"
                            navController.navigate("customer_register")
                        }
                        SelectableButton("Baja", selectedButton == "Baja") {
                            selectedButton = "Baja"
                            navController.navigate("customer_unsubscribe")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Ficha técnica", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = specialty,
                        onValueChange = { specialty = it },
                        placeholder = "Especialidad",
                        leadingIcon = painterResource(id = R.drawable.academic)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = weekHours,
                        onValueChange = { weekHours = it },
                        placeholder = "Horas Semanales",
                        leadingIcon = painterResource(id = R.drawable.clock)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = hourRate,
                        onValueChange = { hourRate = it },
                        placeholder = "Valor Hora",
                        leadingIcon = painterResource(id = R.drawable.money)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* Acción de guardar */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Guardar")
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

