package com.example.appclubdeportivo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.screens.*
import com.example.appclubdeportivo.view_models.PersonViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHandler(navController: NavHostController, appDatabase: AppDatabase) {
    val personViewModel: PersonViewModel = viewModel { PersonViewModel(appDatabase) }

    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController = navController, appDatabase = appDatabase) }
        composable("main_menu") { MainMenuScreen(navController = navController) }
        composable("customer_list") { CustomerListScreen(navController = navController, appDatabase = appDatabase) }
        composable("customer_register") {
            GenericRegisterScreen(
                navController = navController,
                headerTitle = "ABM Cliente",
                nextNavRoute = "customer_register_detail",
                personViewModel = personViewModel
            )
        }
        composable("customer_register_detail") {
            CustomerRegisterDetailScreen(
                navController = navController,
                appDatabase = appDatabase,
                personViewModel = personViewModel
            )
        }
        composable("customer_unsubscribe") { CustomerUnsubscribeScreen(navController = navController, appDatabase = appDatabase) }
        composable("employee_list") { EmployeeListScreen(navController = navController) }
        composable("employee_register") {
            GenericRegisterScreen(
                navController = navController,
                headerTitle = "ABM Empleado",
                nextNavRoute = "employee_register_detail",
                personViewModel = personViewModel
            )
        }
        composable("employee_register_detail") { EmployeeRegisterDetailScreen(navController = navController) }
        composable("employee_unsubscribe") { EmployeeUnsubscribeScreen(navController = navController) }
        composable("payment") { PaymentRegisterFeeScreen(navController = navController) }
        composable("payment_daily") { PaymentDailyScreen(navController = navController) }
        composable("reports") { ReportsScreen(navController = navController) }
        composable("expiration_report") { ExpirationReportScreen(navController = navController) }
        composable("employee_report") { EmployeeReportScreen(navController = navController) }
        composable("customer_report") { CustomerReportScreen(navController = navController) }
        composable("activity_report") { ActivityReportScreen(navController = navController) }
    }
}