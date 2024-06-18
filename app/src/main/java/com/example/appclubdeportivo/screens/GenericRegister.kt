package com.example.appclubdeportivo.screens

import CustomTextField
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appclubdeportivo.R
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.SelectableButton
import java.util.Calendar

@Composable
fun GenericRegisterScreen(navController: NavController, headerTitle: String, nextNavRoute: String) {
    var selectedButton by remember { mutableStateOf("Alta") }
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var bornDate by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }


    AppClubDeportivoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                Header(
                    title = headerTitle,
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
                        value = name,
                        onValueChange = { name = it },
                        placeholder = "Nombre y Apellido",
                        leadingIcon = painterResource(id = R.drawable.person_24px)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = id,
                        onValueChange = { id = it },
                        placeholder = "N° de Documento",
                        leadingIcon = painterResource(id = R.drawable.id_card_24px)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = bornDate,
                        onValueChange = { bornDate = it },
                        placeholder = "Fecha Nacimiento",
                        leadingIcon = painterResource(id = R.drawable.calendar_today_24px),
                        readOnly = true,
                        onClick = {  }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = telephone,
                        onValueChange = { telephone = it },
                        placeholder = "Teléfono",
                        leadingIcon = painterResource(id = R.drawable.telephone),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate(nextNavRoute) },
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