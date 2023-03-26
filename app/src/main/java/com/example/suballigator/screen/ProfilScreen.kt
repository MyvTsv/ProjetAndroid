package com.example.suballigator.screen

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Initiator
import com.example.suballigator.entity.Level
import com.example.suballigator.getLevel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfilScreen(application: Application) {
    var (nameInput, setName) = remember { mutableStateOf(AppDatabase.initiatorConnected!!.name) }
    var (emailInput, setEmail) = remember { mutableStateOf(AppDatabase.initiatorConnected!!.email) }
    var (passwordInput, setPassword) = remember { mutableStateOf(AppDatabase.initiatorConnected!!.password) }
    Scaffold(
        topBar = { TopBar(application, "Sub'Alligator") }
    ) {
        ProfilCard(application = application, nameInput = nameInput, setName = setName, emailInput = emailInput, setEmail = setEmail, passwordInput = passwordInput, setPassword = setPassword)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
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
    val levels : List<Level>? = getLevel(application)
    var expanded by remember {
        mutableStateOf(false)
    }
    var level: Level? = null
    runBlocking {
        //GlobalScope.launch {
            level = AppDatabase.getDatabase(application).levelDAO()
                .getLevelById(AppDatabase.initiatorConnected!!.levelId)
        //}
    }
    var selectedItem by remember {
        mutableStateOf(level?.name)
    }

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
            Text(text = "Niveau", style = MaterialTheme.typography.h6, modifier = Modifier.padding(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 10.dp,
                shape = MaterialTheme.shapes.small,
                content = {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            value = selectedItem ?: "",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(text = "Label") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            levels?.forEach { selectedOption ->
                                DropdownMenuItem(onClick = {
                                    selectedItem = selectedOption.name
                                    expanded = false
                                }) {
                                    Text(text = selectedOption.name)
                                }
                            }
                        }
                    }
                }
            )
            Button(
                onClick = {
                    AppDatabase.initiatorConnected!!.name = nameInput
                    AppDatabase.initiatorConnected!!.email = emailInput
                    AppDatabase.initiatorConnected!!.password = passwordInput
                    GlobalScope.launch {
                        val levelId = AppDatabase.getDatabase(application).levelDAO().getLevelByName(selectedItem!!).id
                        AppDatabase.initiatorConnected!!.levelId = levelId
                        AppDatabase.getDatabase(application).initiatorDAO().update(AppDatabase.initiatorConnected!!)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(80.dp)
            ) {
                Text("Enregistrer")
            }
        }
    }
}