package com.example.appclubdeportivo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ABMCustomerScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var selectedButton by remember { mutableStateOf("Lista") }

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
                    SearchBar(searchText) { searchText = it }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SelectableButton("Lista", selectedButton == "Lista") { selectedButton = "Lista" }
                        SelectableButton("Alta", selectedButton == "Alta") { selectedButton = "Alta" }
                        SelectableButton("Baja", selectedButton == "Baja") { selectedButton = "Baja" }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ClienteCard(
                        numero = "1234",
                        nombre = "Pepe Pepito",
                        fecha = "10.04.2024",
                        monto = "$4500",
                        fechaColor = Color.Red,
                        onEditClick = { /* a implementar */ }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ClienteCard(
                        numero = "1234",
                        nombre = "Pepe Pepin",
                        fecha = "31.12.2024",
                        monto = "$1200",
                        fechaColor = Color.Green,
                        onEditClick = { /* a implementar */ }
                    )

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

@Composable
fun SearchBar(searchText: String, onSearchTextChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.tertiary, CircleShape)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search_24px),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SelectableButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(text)
    }
}

@Composable
fun ClienteCard(
    numero: String,
    nombre: String,
    fecha: String,
    monto: String,
    fechaColor: Color,
    onEditClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) { Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF76ABAE).copy(alpha = 0.8f),
                        Color(0xFF76ABAE).copy(alpha = 0.6f),
                        Color(0xFFF5F5F5).copy(alpha = 1f),
                    )
                )
            )
            .padding(16.dp)
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nº: $numero", style = MaterialTheme.typography.bodyMedium)
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.edit_24px),
                        contentDescription = "Edit Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(nombre, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(fecha, style = MaterialTheme.typography.bodyMedium, color = fechaColor)
                Text(monto, style = MaterialTheme.typography.bodyMedium)
            }
            Text("Vencimiento cuota", style = MaterialTheme.typography.bodySmall)
        }
    }
}
}
