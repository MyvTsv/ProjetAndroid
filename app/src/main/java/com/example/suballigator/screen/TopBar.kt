package com.example.suballigator.screen

import android.app.Application
import android.content.Intent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat.startActivity
import com.example.suballigator.AppDatabase
import com.example.suballigator.LoginScreen

@Composable
fun TopBar(application : Application) {
    TopAppBar(
        title = {
            Text(text = "Sub'Alligator", color = Color.White)
        },
        backgroundColor = Color(red = 30, green = 144, blue = 255),
        contentColor = Color.White,
        actions = {
            IconButton(onClick = { onLogout(application) }) {
                Icon(Icons.Filled.ExitToApp, contentDescription = "Deconnexion")
            }
        }
    )
}

fun onLogout(application: Application) {
    AppDatabase.initiatorConnected = null
    val i = Intent().setClass(application, LoginScreen::class.java)
    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
    application.startActivity(i)
}