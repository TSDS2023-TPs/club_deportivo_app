package com.example.appclubdeportivo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val focusRequesterPassword = remember { FocusRequester() }

    AppClubDeportivoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Header(title = "Bienvenido", showBackButton = false)
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = if (username.isNotEmpty()) ImeAction.Next else ImeAction.Default
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            if (username.isNotEmpty()) {
                                focusRequesterPassword.requestFocus()
                            }
                        }
                    ),
                    decorationBox = { innerTextField ->
                        if (username.isEmpty()) {
                            Text("Usuario", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequesterPassword),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        decorationBox = { innerTextField ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.weight(1f)) {
                                    if (password.isEmpty()) {
                                        Text("Contraseña", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                                    }
                                    innerTextField()
                                }
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        painter = painterResource(
                                            id = if (passwordVisible) R.drawable.visibility_24px else R.drawable.visibility_off_24px
                                        ),
                                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                    )
                                }
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Button(
                    onClick = {
                        if (password.isEmpty()) {
                            errorMessage = "Por favor, ingrese la contraseña."
                        } else if (validateCredentials(username, password)) {
                            navController.navigate("main_menu")
                        } else {
                            errorMessage = "Credenciales inválidas. Por favor, inténtelo de nuevo."
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = username.isNotEmpty() && password.isNotEmpty()
                ) {
                    Text("Iniciar Sesión")
                }
            }
        }
    }
}

fun validateCredentials(username: String, password: String): Boolean {
    return username == "user" && password == "password"
}
