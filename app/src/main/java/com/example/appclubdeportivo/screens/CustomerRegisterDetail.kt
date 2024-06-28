package com.example.appclubdeportivo.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.appclubdeportivo.ui.theme.CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appclubdeportivo.R
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.db_entities.Customer
import com.example.appclubdeportivo.db_entities.Person
import com.example.appclubdeportivo.screens.Header
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.SelectableButton
import com.example.appclubdeportivo.view_models.CustomerViewModel
import com.example.appclubdeportivo.view_models.PersonViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomerRegisterDetailScreen(
    navController: NavController,
    appDatabase: AppDatabase,
    personViewModel: PersonViewModel
) {
    var selectedButton by remember { mutableStateOf("Alta") }
    var physicalCheck by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val customerViewModel: CustomerViewModel = viewModel { CustomerViewModel(appDatabase) }

    val gender by personViewModel.gender.collectAsState()
    val weight by personViewModel.weight.collectAsState()
    val height by personViewModel.height.collectAsState()

    if (showDialog) {
        LaunchedEffect(Unit) {
            delay(2000) 
            showDialog = false
        }
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Cliente registrado con éxito", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }

    AppClubDeportivoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                Header(
                    title = "ABM Cliente",
                    showBackButton = true,
                    colorText = MaterialTheme.colorScheme.onSecondary,
                    backgroundColor = MaterialTheme.colorScheme.tertiary,
                    onBackButtonClick = { navController.popBackStack() }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SelectableButton("Lista", selectedButton == "Lista") {
                            selectedButton = "Lista"
                            navController.navigate("customer_list")
                        }
                        SelectableButton("Alta", selectedButton == "Alta") {
                            selectedButton = "Alta"
                            navController.navigate("customer_register")
                        }
                        SelectableButton("Baja", selectedButton == "Baja") {
                            selectedButton = "Baja"
                            navController.navigate("customer_unsubscribe")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Ficha técnica", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = gender,
                        onValueChange = { personViewModel.updateGender(it) },
                        placeholder = "Género",
                        leadingIcon = painterResource(id = R.drawable.gender)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = weight,
                        onValueChange = { personViewModel.updateWeight(it) },
                        placeholder = "Peso",
                        leadingIcon = painterResource(id = R.drawable.balance)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = height,
                        onValueChange = { personViewModel.updateHeight(it) },
                        placeholder = "Altura",
                        leadingIcon = painterResource(id = R.drawable.rule)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Apto físico")
                        Spacer(modifier = Modifier.width(8.dp))
                        Checkbox(checked = physicalCheck, onCheckedChange = { physicalCheck = it })
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    var selectedOption by remember { mutableStateOf("Socio") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Socio")
                        Spacer(modifier = Modifier.width(8.dp))
                        RadioButton(
                            selected = selectedOption == "Socio",
                            onClick = { selectedOption = "Socio" }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("No Socio")
                        Spacer(modifier = Modifier.width(8.dp))
                        RadioButton(
                            selected = selectedOption == "No Socio",
                            onClick = { selectedOption = "No Socio" }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            Log.d("RegisterButton", "Button clicked")

                            val name = personViewModel.name.value
                            val id = personViewModel.id.value
                            val selectedDocumentType = personViewModel.selectedDocumentType.value
                            val bornDate = personViewModel.bornDate.value
                            val telephone = personViewModel.telephone.value
                            val gender = personViewModel.gender.value
                            val weight = personViewModel.weight.value
                            val height = personViewModel.height.value

                            val person = Person(
                                firstName = name.split(" ").firstOrNull() ?: "",
                                lastName = name.split(" ").lastOrNull() ?: "",
                                identityDocumentNumber = id,
                                birthDate = bornDate,
                                documentTypeId = selectedDocumentType?.documentTypeId ?: 0,
                                gender = gender,
                                telephone = telephone,
                                weightKg = weight.toFloatOrNull() ?: 0f,
                                heightCm = height.toFloatOrNull() ?: 0f
                            )

                            val membership = if (selectedOption == "Socio") "Socio" else "No Socio"
                            val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

                            val customer = Customer(
                                personId = 0,
                                hasPhysicalCheck = physicalCheck,
                                membershipType = membership,
                                startDate = currentDate
                            )

                            customerViewModel.insertCustomer(person, customer)

                            personViewModel.clearFields()
                            navController.navigate("customer_list")
                            showDialog = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Guardar")
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