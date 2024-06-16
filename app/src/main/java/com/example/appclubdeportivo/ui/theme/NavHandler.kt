package com.example.appclubdeportivo.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appclubdeportivo.CustomerListScreen
import com.example.appclubdeportivo.CustomerRegisterDetailScreen
import com.example.appclubdeportivo.CustomerRegisterScreen
import com.example.appclubdeportivo.CustomerUnsubscribeScreen
import com.example.appclubdeportivo.LoginScreen
import com.example.appclubdeportivo.MainMenuScreen

@Composable
fun NavHandler(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController = navController) }
        composable("main_menu") { MainMenuScreen(navController = navController) }
        composable("customer_list") { CustomerListScreen(navController = navController) }
        composable("customer_register") { CustomerRegisterScreen(navController = navController) }
        composable("customer_register_detail") { CustomerRegisterDetailScreen(navController =  navController) }
        composable("customer_unsubscribe") { CustomerUnsubscribeScreen(navController = navController) }
    }
}
