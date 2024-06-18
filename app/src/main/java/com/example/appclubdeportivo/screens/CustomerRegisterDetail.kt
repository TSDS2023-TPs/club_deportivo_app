package com.example.appclubdeportivo.screens

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
import com.example.appclubdeportivo.R
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.CustomTextField
import com.example.appclubdeportivo.ui.theme.SelectableButton

@Composable
fun CustomerRegisterDetailScreen(navController: NavController) {
    var selectedButton by remember { mutableStateOf("Alta") }
    var gender by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var physicalCheck by remember { mutableStateOf(false) }

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
                        value = gender,
                        onValueChange = { gender = it },
                        placeholder = "Género",
                        leadingIcon = painterResource(id = R.drawable.gender)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        placeholder = "weight",
                        leadingIcon = painterResource(id = R.drawable.balance)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = height,
                        onValueChange = { height = it },
                        placeholder = "height",
                        leadingIcon = painterResource(id = R.drawable.rule)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Apto físico")
                        Spacer(modifier = Modifier.width(8.dp))
                        Checkbox(checked = physicalCheck, onCheckedChange = { physicalCheck = it })
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    var selectedOption by remember { mutableStateOf("Socio") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Socio")
                        Spacer(modifier = Modifier.width(8.dp))
                        RadioButton(
                            selected = selectedOption == "Socio",
                            onClick = { selectedOption = "Socio" }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("No Socio")
                        Spacer(modifier = Modifier.width(8.dp))
                        RadioButton(
                            selected = selectedOption == "No Socio",
                            onClick = { selectedOption = "No Socio" }
                        )
                    }

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

