package com.example.appclubdeportivo.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.GenericCard
import com.example.appclubdeportivo.ui.theme.PersonalizedText
import com.example.appclubdeportivo.ui.theme.SelectableButton
import com.example.appclubdeportivo.view_models.CustomerViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun CustomerListScreen(navController: NavController, appDatabase: AppDatabase) {
    var searchText by remember { mutableStateOf("") }
    var selectedButton by remember { mutableStateOf("Lista") }

    val customerViewModel = CustomerViewModel(appDatabase)
    val customers by customerViewModel.customers.observeAsState(initial = emptyList())

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
                    com.example.appclubdeportivo.ui.theme.SearchBar(searchText) { searchText = "Buscar por Documento" }

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

                    LazyColumn {
                        items(customers) { customer ->
                            GenericCard(
                                field1 = PersonalizedText("N° ${customer.id}"),
                                field2 = PersonalizedText(customer.name),
                                field3 = PersonalizedText(
                                    text = customer.expiredDate,
                                    backgroundColor = if (isDateExpired(customer.expiredDate)) Color.Red else Color.Green
                                ),
                                field4 = PersonalizedText("$${customer.amount}"),
                                field5 = PersonalizedText("Vencimiento Cuota"),
                                field6 = PersonalizedText("Monto"),
                                onEditClick = { /* Implement edit functionality */ }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
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


fun isDateExpired(dateString: String): Boolean {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = formatter.parse(dateString)
    return date?.before(Date()) ?: false
}

