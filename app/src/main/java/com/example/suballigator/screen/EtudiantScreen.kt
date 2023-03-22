package com.example.suballigator.screen

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.suballigator.entity.Student
import com.example.suballigator.getStudentNoDeleted
import com.example.suballigator.viewmodel.StudentViewModel
import kotlinx.coroutines.runBlocking


@Composable
fun EtudiantScreen(application: Application) {

    EtudiantList(application)
}

@Composable
fun EtudiantList(application: Application) {
    val listStudent = getStudentNoDeleted(application)
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
        item { HeadEtudiantList() }
        if (listStudent != null) {
            items(listStudent.size) { index ->
                AddEtudiantListRow(student = listStudent[index], index = index)
            }
        }
    }
}

@Composable
fun HeadEtudiantList() {
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
        Text(
            text = "Téléphone",
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AddEtudiantListRow(student: Student, index: Int) {
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
            text = student.name,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
        Text(
            text = student.phone,
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}