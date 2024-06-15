package com.example.appclubdeportivo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainMenuScreen(navController: NavController) {
    AppClubDeportivoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Menú Principal",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("abm_cliente_lista") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ABM Cliente")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("abm_empleado_lista") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ABM Empleado")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("registrar_pago") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Pago")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("reportes") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reportes")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Acción de cerrar sesión */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}
