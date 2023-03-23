package com.example.suballigator.screen

import androidx.compose.foundation.layout.R
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "Sub'Alligator", color = Color.White)
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
    println("Deconnexion")
}