package com.example.appclubdeportivo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.SelectableButton

@Composable
fun CustomerUnsubscribeScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var selectedButton by remember { mutableStateOf("Baja") }
    var customers by remember { mutableStateOf(listOf(Customer("1", "Pepito", activities = listOf(Activity(1, "Natación")), expiredDate = "2024-12-31", amount= "2000"),
        Customer("2", "Pepe", activities = listOf(Activity(1, "Funcional")), expiredDate = "2024-02-21", amount= "4000"))
    )}
    var selectedCustomers by remember { mutableStateOf(setOf<String>()) }

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

                    customers.forEach { customer ->
                        CustomerRow(
                            customer = customer,
                            isChecked = selectedCustomers.contains(customer.id),
                            onCheckedChange = { isChecked ->
                                selectedCustomers = if (isChecked) {
                                    selectedCustomers + customer.id
                                } else {
                                    selectedCustomers - customer.id
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            customers = customers.filterNot { selectedCustomers.contains(it.id) }
                            selectedCustomers = emptySet()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Confirmar")
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
