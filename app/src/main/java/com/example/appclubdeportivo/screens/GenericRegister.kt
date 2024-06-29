package com.example.appclubdeportivo.screens

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appclubdeportivo.R
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.ui.theme.AppClubDeportivoTheme
import com.example.appclubdeportivo.ui.theme.CustomTextField
import com.example.appclubdeportivo.ui.theme.SelectableButton
import com.example.appclubdeportivo.view_models.CustomerViewModel
import com.example.appclubdeportivo.view_models.PersonViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GenericRegisterScreen(
    navController: NavController,
    headerTitle: String,
    nextNavRoute: String,
    personViewModel: PersonViewModel,
    isEditing: Boolean = false,
    appDatabase: AppDatabase,
    customerId: Int? = null
) {
    var selectedButton by remember { mutableStateOf("Alta") }

    val documentTypes by personViewModel.documentTypes.observeAsState(initial = emptyList())
    var isDocumentTypeExpanded by remember { mutableStateOf(false) }
    val customerViewModel: CustomerViewModel = viewModel { CustomerViewModel(appDatabase) }

    val context = LocalContext.current

    val name by personViewModel.name.collectAsState()
    val documentId by personViewModel.documentId.collectAsState()
    val selectedDocumentType by personViewModel.selectedDocumentType.collectAsState()
    val bornDate by personViewModel.bornDate.collectAsState()
    val telephone by personViewModel.telephone.collectAsState()

    LaunchedEffect(customerId) {
        if (isEditing && customerId != null) {
            customerViewModel.getCustomerPersonById(customerId).observeForever { customer ->
                customer?.let {
                    personViewModel.updateName("${it.firstName} ${it.lastName}")
                    personViewModel.updateId(it.personId)
                    personViewModel.updateDocumentId(it.documentId)
                    personViewModel.updateTelephone(it.telephone)
                    personViewModel.updateBornDate(it.birthDate)
                    personViewModel.updateTelephone(it.telephone)
                    personViewModel.updateGender(it.gender)
                    personViewModel.updateWeight(it.weight.toString())
                    personViewModel.updateHeight(it.height.toString())
                    val documentType = documentTypes.firstOrNull { dt -> dt.documentTypeId == it.documentType }
                    personViewModel.updateSelectedDocumentType(documentType)
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
                    title = headerTitle,
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

                    Text("Datos Personales", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = name,
                        onValueChange = { personViewModel.updateName(it) },
                        placeholder = "Nombre y Apellido",
                        leadingIcon = painterResource(id = R.drawable.person_24px)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(modifier = Modifier.fillMaxWidth()) {
                        ExposedDropdownMenuBox(
                            expanded = isDocumentTypeExpanded,
                            onExpandedChange = { isDocumentTypeExpanded = !isDocumentTypeExpanded }
                        ) {
                            TextField(
                                value = selectedDocumentType?.description ?: "Seleccione tipo de documento",
                                onValueChange = {},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                readOnly = true,
                                placeholder = {
                                    Text("Seleccione tipo de documento")
                                },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = isDocumentTypeExpanded
                                    )
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors()
                            )
                            ExposedDropdownMenu(
                                expanded = isDocumentTypeExpanded,
                                onDismissRequest = { isDocumentTypeExpanded = false },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(if (isDocumentTypeExpanded) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.background)
                            ) {
                                documentTypes.forEach { documentType ->
                                    DropdownMenuItem(
                                        text = { Text(documentType.description) },
                                        onClick = {
                                            personViewModel.updateSelectedDocumentType(documentType)
                                            isDocumentTypeExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = documentId,
                        onValueChange = { personViewModel.updateDocumentId(it) },
                        placeholder = "N° de Documento",
                        leadingIcon = painterResource(id = R.drawable.id_card_24px),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        readOnly = isEditing
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = bornDate,
                        onValueChange = { personViewModel.updateBornDate(it) },
                        placeholder = "Fecha Nacimiento",
                        leadingIcon = painterResource(id = R.drawable.calendar_today_24px),
                        onClick = {
                            val calendar = Calendar.getInstance()
                            val datePickerDialog = DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                                    personViewModel.updateBornDate(selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                            )
                            datePickerDialog.show()
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = telephone,
                        onValueChange = { personViewModel.updateTelephone(it) },
                        placeholder = "Teléfono",
                        leadingIcon = painterResource(id = R.drawable.telephone),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            navController.navigate(nextNavRoute)
                         },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Siguiente")
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
