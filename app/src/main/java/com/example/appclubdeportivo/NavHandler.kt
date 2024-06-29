package com.example.appclubdeportivo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                personViewModel = personViewModel,
                appDatabase = appDatabase
            )
        }
        composable("customer_edit/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val customerId = it.arguments?.getInt("id") ?: -1  // Maneja el caso donde id es null
            if (customerId != -1) {
                GenericRegisterScreen(
                    navController = navController,
                    headerTitle = "ABM Cliente",
                    nextNavRoute = "customer_edit_detail/${customerId}",
                    personViewModel = personViewModel,
                    appDatabase = appDatabase,
                    isEditing = true,
                    customerId = customerId
                )
            }
        }
        composable("customer_edit_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val customerId = it.arguments?.getInt("id") ?: -1
            if (customerId != -1) {
                CustomerRegisterDetailScreen(
                    navController = navController,
                    personViewModel = personViewModel,
                    appDatabase = appDatabase,
                    isEditing = true,
                    customerId = customerId
                )
            }
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
                personViewModel = personViewModel,
                appDatabase = appDatabase
            )
        }
        composable("employee_register_detail") { EmployeeRegisterDetailScreen(navController = navController) }
        composable("employee_unsubscribe") { EmployeeUnsubscribeScreen(navController = navController) }
        composable("payment") { PaymentRegisterFeeScreen(navController = navController, appDatabase = appDatabase) }
        composable("payment_daily") { PaymentDailyScreen(navController = navController, appDatabase = appDatabase) }
        composable("reports") { ReportsScreen(navController = navController) }
        composable("expiration_report") { ExpirationReportScreen(navController = navController, appDatabase = appDatabase) }
        composable("employee_report") { EmployeeReportScreen(navController = navController) }
        composable("customer_report") { CustomerReportScreen(navController = navController) }
        composable("activity_report") { ActivityReportScreen(navController = navController) }
    }
}