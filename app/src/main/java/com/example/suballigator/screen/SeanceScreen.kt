package com.example.suballigator.screen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.suballigator.*
import com.example.suballigator.entity.Session
import com.example.suballigator.entity.Student

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SeanceScreen(application: Application) {
    Scaffold(
        topBar = { TopBar() }
    ) {
        CreateSessionList(sessions = getSession(application = application))
    }
}

@Composable
fun CreateSessionList(sessions: List<Session>?) {
    if( sessions?.size == 0 ) {
        Text(text = "Aucune séance n'a été trouvée", modifier = Modifier.padding(16.dp))
        return
    }
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item { AddHeadRow(listOf(
            "Date"
        )) }
        sessions?.size?.let {
            items(it) { index ->
                AddSessionDataRow(session = sessions.get(index), index)
            }
        }
    }
}

@Composable
fun AddSessionDataRow(session: Session, index: Int) {
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
            text = session.date,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
        AddSessionDetailCell(session = session)
    }
}

@Composable
fun AddSessionDetailCell(session: Session) {
    var showCard by remember { mutableStateOf(false) }
    Button(onClick = { showCard = true }) {
        Text(text = "Afficher la carte")
    }
    if (showCard) {
        showCard = showSessionCard(session = session)
    }
}

@Composable
fun showSessionCard(session: Session): Boolean {
    var showCard by remember { mutableStateOf(true) }
    if(showCard) {
        AlertDialog(
            onDismissRequest = { showCard = false },
            title = { Text(text = "Titre de la carte") },
            text = { Text(text = "Contenu de la carte") },
            confirmButton = {
                Button(
                    onClick = { showCard = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(text = "Fermer")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
    return showCard
}
