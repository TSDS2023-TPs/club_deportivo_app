package com.example.appclubdeportivo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appclubdeportivo.view_entities.Payment
import java.text.SimpleDateFormat

import java.util.Date
import java.util.Locale

@Composable
fun PaymentCard(payment: Payment, isSelected: Boolean, onSelect: () -> Unit) {
    val cardBackgroundColor = Color(0xFF76ABAE).copy(alpha = 0.4f)
    var selectedPaymentMethod by remember { mutableStateOf("Efectivo") }
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val paymentDate = formatter.format(Date())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onSelect)
        ,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                else
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    )) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "N° Socio ${payment.customerId}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Cuota ${payment.monthYear}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Fecha Vencimiento", fontSize = 14.sp, color = Color.Gray)
                    Text(payment.dueDate, fontSize = 14.sp)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Fecha de pago", fontSize = 14.sp, color = Color.Gray)
                    Text(paymentDate.toString(), fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Monto", fontSize = 14.sp, color = Color.Gray)
            Text("$${payment.amount}", fontSize = 18.sp, fontWeight = FontWeight.Bold)

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
        }
    }
}