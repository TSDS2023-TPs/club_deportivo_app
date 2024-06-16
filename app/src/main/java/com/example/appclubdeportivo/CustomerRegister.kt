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

@Composable
fun CustomerRegisterScreen(navController: NavController) {
    var selectedButton by remember { mutableStateOf("Alta") }
    var nombre by remember { mutableStateOf("") }
    var documento by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var aptoFisico by remember { mutableStateOf(false) }

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

                    Text("Datos Personales", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        placeholder = "Nombre y Apellido",
                        leadingIcon = painterResource(id = R.drawable.person_24px)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = documento,
                        onValueChange = { documento = it },
                        placeholder = "N° de Documento",
                        leadingIcon = painterResource(id = R.drawable.id_card_24px)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = fechaNacimiento,
                        onValueChange = { fechaNacimiento = it },
                        placeholder = "Fecha Nacimiento",
                        leadingIcon = painterResource(id = R.drawable.calendar_today_24px)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = telefono,
                        onValueChange = { telefono = it },
                        placeholder = "Teléfono",
                        leadingIcon = painterResource(id = R.drawable.telephone)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("customer_register_detail") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Siguiente")
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

