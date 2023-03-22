package com.example.suballigator.screen

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.suballigator.entity.Formation
import com.example.suballigator.getFormation
import com.example.suballigator.*
import com.example.suballigator.entity.Session

@Composable
fun SeanceScreen(application: Application) {
    SeanceList(application = application)
}

@Composable
fun SeanceList(application: Application) {
    val listSeance = getSession(application)
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item { HeadSeanceList() }
        if (listSeance != null) {
            items(listSeance.size) { index ->
                AddSeanceListRow(seance = listSeance[index], index = index)
            }
        }
    }
}

@Composable
fun HeadSeanceList() {
    Row(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(Color.Cyan)
        .padding(vertical = 20.dp, horizontal = 1.dp)
    ) {
        Text(
            text = "Date",
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AddSeanceListRow(seance: Session, index: Int) {
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
            text = seance.date,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}