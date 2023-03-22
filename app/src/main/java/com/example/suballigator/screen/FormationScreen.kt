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
import com.example.suballigator.entity.Student
import com.example.suballigator.getFormation
import com.example.suballigator.getStudentNoDeleted

@Composable
fun FormationScreen(application: Application) {
    FormationList(application = application)
}

@Composable
fun FormationList(application: Application) {
    val listFormation = getFormation(application)
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item { HeadFormationList() }
        if (listFormation != null) {
            items(listFormation.size) { index ->
                AddFormationListRow(formation = listFormation[index], index = index)
            }
        }
    }
}

@Composable
fun HeadFormationList() {
    Row(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(Color.Cyan)
        .padding(vertical = 20.dp, horizontal = 1.dp)
    ) {
        Text(
            text = "Nom",
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AddFormationListRow(formation: Formation, index: Int) {
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
    }
}