package com.example.appclubdeportivo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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
                        onClick = { navController.navigate("abm_cliente_lista") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("ABM Cliente")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("abm_empleado_lista") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("ABM Empleado")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("registrar_pago") },
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar Pago")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("reportes") },
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
