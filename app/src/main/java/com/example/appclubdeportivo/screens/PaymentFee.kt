package com.example.appclubdeportivo.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider

import androidx.navigation.NavController
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.SearchBar
import com.example.appclubdeportivo.view_models.PaymentViewModel
import java.io.File

@Composable
fun PaymentRegisterFeeScreen(navController: NavController, appDatabase: AppDatabase) {
    var emitticket by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }
    val paymentViewModel = PaymentViewModel(appDatabase)
    val paymentCards by paymentViewModel.paymentCards.observeAsState(initial = emptyList())
    val selectedPayments by paymentViewModel.selectedPayments.collectAsState()
    val totalAmount by paymentViewModel.totalAmount.collectAsState()

    val pdfUri by paymentViewModel.pdfUri.collectAsState()
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/pdf")) { uri ->
        uri?.let { selectedUri ->
            context.contentResolver.openOutputStream(selectedUri)?.use { outputStream ->
                context.contentResolver.openInputStream(pdfUri!!)?.use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            openPDF(context, selectedUri)
        }
    }

    val filteredPayments = paymentCards.filter { paymentCard ->
        paymentCard.customerId.contains(searchText)
    }

    AppClubDeportivoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Header(
                title = "Registrar Pago",
                showBackButton = true,
                colorText = MaterialTheme.colorScheme.onSecondary,
                backgroundColor = MaterialTheme.colorScheme.tertiary,
                onBackButtonClick = { navController.popBackStack() }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SearchBar(searchText) { searchText = it }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    items(filteredPayments, key = { it.feeId }) { payment ->
                        PaymentCard(
                            payment = payment,
                            isSelected = selectedPayments.contains(payment.feeId),
                            onSelect = {
                                paymentViewModel.togglePaymentSelection(payment)
                            }
                        )
                        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Emitir Comprobante")
                        Spacer(modifier = Modifier.width(8.dp))
                        Checkbox(checked = emitticket, onCheckedChange = { emitticket = it })
                    }
                    Text(
                        text = "Total",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "$${"%.2f".format(totalAmount)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(24.dp))


                    Button(
                        onClick = {
                            paymentViewModel.registerPayment(context, emitticket)
                            if (emitticket) {
                                launcher.launch("Factura_${System.currentTimeMillis()}.pdf")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(text = "Registrar")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

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

fun openPDF(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(uri, "application/pdf")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(intent)
}

