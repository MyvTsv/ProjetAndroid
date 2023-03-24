package com.example.suballigator.screen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfilScreen(application: Application) {
    Scaffold(
        topBar = { TopBar(application) }
    ) {
        ProfilCard(application = application)
    }
}

@Composable
fun ProfilCard(application: Application) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 10.dp,
        shape = MaterialTheme.shapes.small,
        content = {
            Text("Card with background color argument", modifier = Modifier.padding(16.dp))
        }
    )
}