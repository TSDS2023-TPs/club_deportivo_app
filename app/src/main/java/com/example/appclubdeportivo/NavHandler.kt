package com.example.appclubdeportivo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHandler(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController = navController) }
        composable("main_menu") { MainMenuScreen(navController = navController) }
        composable("abm_cliente_lista") { ABMCustomerScreen(navController = navController) }
        /*composable("abm_cliente_alta") { ABMClienteAltaScreen() }
        composable("abm_cliente_baja") { ABMClienteBajaScreen() }*/
        // Añadir más pantallas según sea necesario
    }
}
