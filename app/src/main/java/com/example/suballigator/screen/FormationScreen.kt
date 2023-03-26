package com.example.suballigator.screen

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.suballigator.activity.SeanceByFormationScreen
import com.example.suballigator.entity.Formation
import com.example.suballigator.getFormation
import com.example.suballigator.getLevelById

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FormationScreen(application: Application) {
    Scaffold(
        topBar = { TopBar(application, "Sub'Alligator") }
    ) {
        CreateFormationList(formations = getFormation(application = application), application = application)
    }
}

@Composable
fun CreateFormationList(formations: List<Formation>?, application: Application) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item { AddHeadRow(listOf(
            "Nom"
        )) }
        formations?.size?.let {
            items(it) { index ->
                AddFormationDataRow(formation = formations.get(index), index = index, application = application)
            }
        }
    }
}

@Composable
fun AddFormationDataRow(formation: Formation, index: Int, application: Application) {
    var colorRow = Color(red = 0xF1, green = 0xF1, blue = 0xF1)
    if (index % 2 == 0) {
        colorRow = Color(red = 0xE1, green = 0xE1, blue = 0xE1)
    }
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(colorRow)
            .padding(vertical = 20.dp, horizontal = 1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formation.name,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
        AddFormationDetailCell(formation = formation, application = application)
    }
}

@Composable
fun AddFormationDetailCell(formation: Formation, application: Application) {
    var showCard by remember { mutableStateOf(false) }
    Button(onClick = { showCard = true }) {
        Text(text = "Voir plus")
    }
    if (showCard) {
        showCard = showFormationCard(formation = formation, application = application)
    }
}

@Composable
fun showFormationCard(formation: Formation, application: Application): Boolean {
    var showCard by remember { mutableStateOf(true) }
    val level = getLevelById(application, formation.levelId)
    val context = LocalContext.current
    if (showCard) {
        AlertDialog(
            onDismissRequest = { showCard = false },
            title = { Text(
                text = formation.name,
                fontWeight = FontWeight.Bold) },
            text = { Text(text = "Level : " + level?.name) },
            confirmButton = {
                Button(
                    onClick = { showCard = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(text = "Fermer")
                }
                Button(
                    onClick = { showCard = false
                        val intent = Intent(context, SeanceByFormationScreen::class.java).apply {
                            putExtra("formationId", formation.id)
                        }
                        context.startActivity(intent)},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                ) {
                    Text(text = "Voir les séances")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
    return showCard
}