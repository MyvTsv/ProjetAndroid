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
import com.example.suballigator.activity.EvaluationScreen
import com.example.suballigator.activity.SeanceByFormationScreen
import com.example.suballigator.entity.Formation
import com.example.suballigator.entity.Student
import com.example.suballigator.getFormationById
import com.example.suballigator.getLevelById
import com.example.suballigator.getStudentByFormationId
import com.example.suballigator.getStudentNoDeleted


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EtudiantScreen(application: Application) {
    Scaffold(
        topBar = { TopBar() },
        backgroundColor = Color(red = 0xF9, green = 0xF9, blue = 0xF9),
        content = {
            CreateStudentList(application = application, getStudentNoDeleted(application = application))
        }
    )
}

@Composable
fun CreateStudentList(application: Application, students: List<Student>?) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item { AddHeadRow(listOf(
            "Nom"
        )) }
        students?.size?.let {
            items(it) { index ->
                AddStudentDataRow(application = application, student = students.get(index), index)
            }
        }
    }
}

@Composable
fun AddStudentDataRow(application: Application, student: Student, index: Int) {
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
        AddStudentDetailCell(application = application, student)
    }
}

@Composable
fun AddStudentDetailCell(application: Application, student: Student) {
    var showCard by remember { mutableStateOf(false) }
    Button(onClick = { showCard = true }) {
        Text(text = "Voir plus")
    }
    if (showCard) {
        showCard = showStudentCard(application = application, student = student)
    }
}

@Composable
fun showStudentCard(application: Application, student: Student): Boolean {
    val context = LocalContext.current
    var showCard by remember { mutableStateOf(true) }
    var formation = getFormationById(application, student.formationId)
    var level = getLevelById(application, formation.levelId)
    if(showCard) {
        AlertDialog(
            onDismissRequest = { showCard = false },
            title = { Text(
                text = student.name,
                fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(
                        text = "Téléphone : " + student.phone,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Formation : " + formation.name,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Level : " + level?.name,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { showCard = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(text = "Fermer")
                }
                Button(
                    onClick = { showCard = false
                        val intent = Intent(context, EvaluationScreen::class.java).apply {
                            putExtra("studentId", student.id)
                        }
                        context.startActivity(intent)},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                ) {
                    Text(text = "Evaluation")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
    return showCard
}