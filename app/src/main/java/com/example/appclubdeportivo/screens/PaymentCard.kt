package com.example.appclubdeportivo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appclubdeportivo.view_entities.Payment

@Composable
fun PaymentCard(payment: Payment) {
    val cardBackgroundColor = Color(0xFFE0F7FA).copy(alpha = 0.39f)

    // Mantener el estado del RadioButton seleccionado
    val selectedPaymentMethod = remember { mutableStateOf("Tarjeta") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardBackgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "N° Socio ${payment.customerId}", style = TextStyle(fontSize = 16.sp))
            Text(text = "Cuota ${payment.monthYear}", style = TextStyle(fontSize = 16.sp))
            Text(text = "Fecha Vencimiento ${payment.dueDate}", style = TextStyle(fontSize = 14.sp))
            Text(text = "Fecha de pago ${payment.paymentDate}", style = TextStyle(fontSize = 14.sp))
            Text(text = "Monto ${payment.amount}", style = TextStyle(fontSize = 14.sp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.clickable {
                    selectedPaymentMethod.value = "Tarjeta"
                }) {
                    RadioButton(
                        selected = selectedPaymentMethod.value == "Tarjeta",
                        onClick = { selectedPaymentMethod.value = "Tarjeta" }
                    )
                    Text(text = "Tarjeta", modifier = Modifier.align(Alignment.CenterVertically))
                }
                Row(modifier = Modifier.clickable {
                    selectedPaymentMethod.value = "Efectivo"
                }) {
                    RadioButton(
                        selected = selectedPaymentMethod.value == "Efectivo",
                        onClick = { selectedPaymentMethod.value = "Efectivo" }
                    )
                    Text(text = "Efectivo", modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    }
}
