package com.example.appclubdeportivo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.data.UserDao
import com.example.appclubdeportivo.screens.ActivityReportScreen
import com.example.appclubdeportivo.screens.CustomerListScreen
import com.example.appclubdeportivo.screens.CustomerRegisterDetailScreen
import com.example.appclubdeportivo.screens.CustomerReportScreen
import com.example.appclubdeportivo.screens.CustomerUnsubscribeScreen
import com.example.appclubdeportivo.screens.EmployeeListScreen
import com.example.appclubdeportivo.screens.EmployeeRegisterDetailScreen
import com.example.appclubdeportivo.screens.EmployeeReportScreen
import com.example.appclubdeportivo.screens.EmployeeUnsubscribeScreen
import com.example.appclubdeportivo.screens.ExpirationReportScreen
import com.example.appclubdeportivo.screens.GenericRegisterScreen
import com.example.appclubdeportivo.screens.LoginScreen
import com.example.appclubdeportivo.screens.MainMenuScreen
import com.example.appclubdeportivo.screens.PaymentRegisterScreen
import com.example.appclubdeportivo.screens.ReportsScreen


@Composable
fun NavHandler(navController: NavHostController, appDatabase: AppDatabase) {
    NavHost(navController, startDestination = "login") {

        composable("login") { LoginScreen(navController = navController, appDatabase = appDatabase) }
        composable("main_menu") { MainMenuScreen(navController = navController) }
        composable("customer_list") { CustomerListScreen(navController = navController, appDatabase = appDatabase) }
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
