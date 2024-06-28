package com.example.appclubdeportivo.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appclubdeportivo.screens.Header
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme

@Composable
fun MainMenuScreen(navController: NavController) {

    AppClubDeportivoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                Header(title = "Menú", showBackButton = false, colorText = MaterialTheme.colorScheme.onSecondary, backgroundColor = MaterialTheme.colorScheme.tertiary)
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Button(
                        onClick = { navController.navigate("customer_list") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("ABM Cliente")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("employee_list") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("ABM Empleado")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("payment") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar Pago Socio")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { navController.navigate("payment_daily") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar Pago No Socio")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("reports") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Reportes")
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
