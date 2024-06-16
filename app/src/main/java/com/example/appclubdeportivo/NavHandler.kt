package com.example.appclubdeportivo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavHandler(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController = navController) }
        composable("main_menu") { MainMenuScreen(navController = navController) }
        composable("customer_list") { CustomerListScreen(navController = navController) }
        composable("customer_register") { GenericRegisterScreen(navController = navController , headerTitle = "ABM Cliente", nextNavRoute = "customer_register_detail") }
        composable("customer_register_detail") { CustomerRegisterDetailScreen(navController =  navController) }
        composable("customer_unsubscribe") { CustomerUnsubscribeScreen(navController = navController) }
        composable("employee_list") { EmployeeListScreen(navController = navController) }
        composable("employee_register") { GenericRegisterScreen(navController = navController , headerTitle = "ABM Empleado", nextNavRoute = "employee_register_detail") }
        composable("employee_register_detail") { EmployeeRegisterDetailScreen(navController = navController) }
        composable("employee_unsubscribe") { EmployeeUnsubscribeScreen(navController = navController) }
        composable("payment") { PaymentRegisterScreen(navController = navController) }
        composable("reports") { ReportsScreen(navController = navController) }
        composable("expiration_report") { ExpirationReportScreen(navController = navController) }
        composable("employee_report") { EmployeeReportScreen(navController = navController) }
        composable("customer_report") { CustomerReportScreen(navController = navController) }
        composable("activity_report") { ActivityReportScreen(navController = navController) }

    }
}
