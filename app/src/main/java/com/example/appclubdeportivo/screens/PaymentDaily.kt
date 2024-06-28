package com.example.appclubdeportivo.screens

import com.example.appclubdeportivo.ui.theme.CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.appclubdeportivo.R
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import kotlinx.coroutines.delay


@Composable
fun PaymentDailyScreen(
    navController: NavController
) {
    var emitticket by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dni by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    if (showDialog) {
        LaunchedEffect(Unit) {
            delay(2000)
            showDialog = false
        }
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Pago registrado con éxito", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }


    AppClubDeportivoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                Header(
                    title = "Registrar Pago",
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

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Registrar pago diario", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = dni,
                        onValueChange = { dni = it },
                        placeholder = "DNI",
                        leadingIcon = painterResource(id = R.drawable.id_card_24px)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        placeholder = "Monto",
                        leadingIcon = painterResource(id = R.drawable.money)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Emitir Comprobante")
                        Spacer(modifier = Modifier.width(8.dp))
                        Checkbox(checked = emitticket, onCheckedChange = { emitticket = it })
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Button(
                        onClick = {
                            // Logica que guarda el pago
                            //FindCustomerById()
                            //RegisterPayment()
                            showDialog = true
                            dni = ""
                            amount = ""
                            },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar Pago")
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

