package com.example.suballigator.screen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.suballigator.AppDatabase
import androidx.compose.runtime.*

@Composable
fun TopBar(title: String = "Sub'Alligator") {
    TopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        backgroundColor = Color(red = 30, green = 144, blue = 255),
        contentColor = Color.White,
        actions = {
            IconButton(onClick = { onLogout() }) {
                Icon(Icons.Filled.ExitToApp, contentDescription = "Deconnexion")
            }
        }
    )
}

fun onLogout() {
    //AppDatabase.initiatorConnected = null
}