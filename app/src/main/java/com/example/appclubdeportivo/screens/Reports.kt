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
fun ReportsScreen(navController: NavController) {

    AppClubDeportivoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                Header(title = "Reportes", showBackButton = true, colorText = MaterialTheme.colorScheme.onSecondary, backgroundColor = MaterialTheme.colorScheme.tertiary, onBackButtonClick = { navController.popBackStack() })
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Button(
                        onClick = { navController.navigate("expiration_report") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Vencimientos")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("customer_report") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Clientes")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("employee_report") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Empleados")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("activity_report") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Actividades")
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
