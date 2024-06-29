package com.example.appclubdeportivo.screens

import android.util.Log
import com.example.appclubdeportivo.ui.theme.CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.appclubdeportivo.R
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.view_models.CustomerViewModel
import com.example.appclubdeportivo.view_models.PaymentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun PaymentDailyScreen(
    navController: NavController,
    appDatabase: AppDatabase
) {
    var emitticket by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedPaymentMethod by remember { mutableStateOf("Efectivo") }
    var dni by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val paymentViewModel = PaymentViewModel(appDatabase)
    val customerViewModel = CustomerViewModel(appDatabase)

    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()



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
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedPaymentMethod == "Tarjeta",
                                onClick = { selectedPaymentMethod = "Tarjeta" },
                            )
                            Text("Tarjeta", fontSize = 14.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedPaymentMethod == "Efectivo",
                                onClick = { selectedPaymentMethod = "Efectivo" },
                            )
                            Text("Efectivo", fontSize = 14.sp)
                        }
                    }
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

                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Button(
                        onClick = {
                            if (dni.isNotEmpty() && amount.isNotEmpty()) {
                                val customerId = customerViewModel.getCustomerIdByDocumentId(dni)
                                if (customerId  > 0) {
                                    val amountDouble = amount.toDoubleOrNull()
                                    if (amountDouble != null) {
                                        coroutineScope.launch {
                                            val success = paymentViewModel.registerSimplePayment(
                                                context,
                                                customerId,
                                                amountDouble,
                                                selectedPaymentMethod,
                                                emitticket
                                            )
                                            if (success) {
                                                showDialog = true
                                                dni = ""
                                                amount = ""
                                                errorMessage = ""
                                            } else {
                                                errorMessage = "Error al registrar el pago"
                                            }
                                        }
                                    } else {
                                        errorMessage = "Monto inválido"
                                    }
                                } else {
                                    errorMessage = "Cliente no encontrado"
                                }
                            } else {
                                errorMessage = "Por favor, complete todos los campos"
                            }
                        }
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

