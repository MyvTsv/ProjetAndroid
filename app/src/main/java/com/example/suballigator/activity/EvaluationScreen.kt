package com.example.suballigator.activity

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.suballigator.*
import com.example.suballigator.activity.ui.theme.SubAlligatorTheme
import com.example.suballigator.entity.Content
import com.example.suballigator.entity.Participant
import com.example.suballigator.screen.AddHeadRow
import com.example.suballigator.screen.CreateSessionList
import com.example.suballigator.screen.TopBar

class EvaluationScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idStudent = intent.getIntExtra("studentId", 1)
        val student = getStudentById(application = application, id = idStudent)
        setContent {
            SubAlligatorTheme {

                Scaffold(
                    topBar = { TopBar(application, "Evaluation de " + student?.name) },
                    content = { CreateEvaluationList(participants = getParticipantByStudentId(application = application, id = idStudent), application = application)
                        Button(
                            onClick = {
                                finish()
                            }
                        ) {
                            Text(text = "Revenir à la liste des étudiants")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CreateEvaluationList(application: Application, participants: List<Participant>?) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item { AddHeadRow(title = listOf(
            "Contenu",
            "Status"
        ))}
        participants?.size?.let {
            items(it) { index ->
                AddEvaluationDataRow(participant = participants[index], index = index, application = application)
            }
        }
    }
}

@Composable
fun AddEvaluationDataRow(participant: Participant, index: Int, application: Application) {
    var content = getContentById(application = application, id = participant.contentId)
    var aptitude = getAptitudeById(application = application, id = content.aptitudeId)
    var status = getStatusById(application = application, id = participant.statusId)
    val hexColor = status?.color
    val intColor = hexColor?.substring(1)?.toInt(16)
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
            text = aptitude.name,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
        Text(
            text = status!!.name,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(color = intColor!!)),
            textAlign = TextAlign.Center
        )
        AddAptitudeDetailCell(aptitude, commentary = participant.commentary ,application = application)
    }
}