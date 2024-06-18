package com.example.appclubdeportivo.screens

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
import com.example.appclubdeportivo.view_entities.Customer
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CustomerListScreen(navController: NavController) {
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
                    com.example.appclubdeportivo.ui.theme.SearchBar(searchText) { searchText = it }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SelectableButton("Lista", selectedButton == "Lista") { selectedButton = "Lista" }
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

                    val exampleCustomers = listOf(
                        Customer("1234", "Pepe Pepito", expiredDate = "10-04-2024", amount= "$4500"),
                        Customer("1235", "Pepe Pepin", expiredDate ="31-12-2024", amount="$1200")
                    )

                    val currentDate = Calendar.getInstance().time
                    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

                    for (customer in exampleCustomers) {
                        val expirationDate = dateFormat.parse(customer.expiredDate)

                        val backgroundColor = if (expirationDate < currentDate) {
                            Color.Red
                        } else {
                            Color.Green
                        }

                        GenericCard(
                            field1 = PersonalizedText(customer.id),
                            field2 = PersonalizedText(customer.name),
                            field3 = PersonalizedText(customer.expiredDate, backgroundColor = backgroundColor),
                            field4 = PersonalizedText(customer.amount, backgroundColor = Color.White),
                            field5 = PersonalizedText("Vencimiento Cuota"),
                            field6 = PersonalizedText("Monto"),
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




