package com.example.suballigator.screen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.suballigator.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfilScreen(application: Application) {
    var (nameInput, setName) = remember { mutableStateOf(AppDatabase.initiatorConnected!!.name) }
    var (emailInput, setEmail) = remember { mutableStateOf(AppDatabase.initiatorConnected!!.email) }
    var (passwordInput, setPassword) = remember { mutableStateOf(AppDatabase.initiatorConnected!!.password) }
    Scaffold(
        topBar = { TopBar(application) }
    ) {
        ProfilCard(application = application, nameInput = nameInput, setName = setName, emailInput = emailInput, setEmail = setEmail, passwordInput = passwordInput, setPassword = setPassword)
    }
}

@Composable
fun ProfilCard(
    application: Application,
    nameInput: String,
    setName: (String) -> Unit,
    emailInput: String,
    setEmail: (String) -> Unit,
    passwordInput: String,
    setPassword: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "User Icon"
                )
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth()
                )
            }
            Text("Nom", style = MaterialTheme.typography.h6, modifier = Modifier.padding(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 10.dp,
                shape = MaterialTheme.shapes.small,
                content = {
                    TextField(
                        value = nameInput,
                        onValueChange = setName,
                        modifier = Modifier.padding(16.dp),
                        placeholder = { Text("Insérer un nom") }
                    )
                }
            )
            Text("Email", style = MaterialTheme.typography.h6, modifier = Modifier.padding(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 10.dp,
                shape = MaterialTheme.shapes.small,
                content = {
                    TextField(
                        value = emailInput,
                        onValueChange = setEmail,
                        modifier = Modifier.padding(16.dp),
                        placeholder = { Text("Insérer une e-mail valide") }
                    )
                }
            )
            Text(
                "Mot de passe",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(12.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 10.dp,
                shape = MaterialTheme.shapes.small,
                content = {
                    TextField(
                        value = passwordInput,
                        onValueChange = setPassword,
                        modifier = Modifier.padding(16.dp),
                        placeholder = { Text("Insérer un mot de passe") }
                    )
                }
            )
            Button(
                onClick = {
                    AppDatabase.initiatorConnected!!.name = nameInput
                    AppDatabase.initiatorConnected!!.email = emailInput
                    AppDatabase.initiatorConnected!!.password = passwordInput
                    GlobalScope.launch {
                        AppDatabase.updateInitiator(application = application)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Enregistrer")
            }
        }
    }
}