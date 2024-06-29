package com.example.appclubdeportivo.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.view_entities.CustomerCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ExpirationReportScreen(navController: NavController, appDatabase: AppDatabase) {
    val expiredCustomers = remember {
        mutableStateOf(emptyList<CustomerCard>())
    }

    LaunchedEffect(Unit) {
        val customers = withContext(Dispatchers.IO) {
            appDatabase.customerDao().getAllCustomers().filter {
                it.expiredDate.let { date ->
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)?.before(Date()) == true
                }
            }
        }
        expiredCustomers.value = customers
    }

    val reportData = expiredCustomers.value.map {
        listOf(it.id, it.name, it.expiredDate)
    }
    val headers = listOf("N°", "Nombre", "Fecha de Vencimiento")

    GenericReport(
        navController = navController,
        title = "Vencimientos Reporte",
        listTitle = "Listado vencimientos",
        data = reportData,
        headers = headers
    )
}
