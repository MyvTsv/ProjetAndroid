package com.example.suballigator.activity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.suballigator.*
import com.example.suballigator.activity.ui.theme.SubAlligatorTheme
import com.example.suballigator.entity.Aptitude
import com.example.suballigator.entity.Content
import com.example.suballigator.entity.Formation
import com.example.suballigator.screen.*

class ContentScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var idSession = intent.getIntExtra("sessionId", 0)
        var session = getSessionById(application = application, id = idSession)
        setContent {
            SubAlligatorTheme {

                Scaffold(
                    topBar = { TopBar(application,"Detail de la séance du " + session?.date) },
                    content = {
                        CreateContentList(
                            contents = getContentBySessionId(application = application,
                                id = idSession
                            ),
                            application = application
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun CreateContentList(application: Application, contents: List<Content>?) {
    Column {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                AddHeadRow(title = listOf("Aptitude travaillé"))
            }
            contents?.let {
                items(it.size) { index ->
                    AddContentDataRow(content = it[index], index = index, application = application)
                }
            }
        }
    }
}


@Composable
fun AddContentDataRow(content: Content, index: Int, application: Application) {
    var aptitude = getAptitudeById(application = application, id = content.aptitudeId)
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
            textAlign = TextAlign.Center,
        )
        AddAptitudeDetailCell(aptitude, application = application)
    }
}

@Composable
fun AddAptitudeDetailCell(aptitude: Aptitude, commentary: String? = null, application: Application) {
    var showCard by remember { mutableStateOf(false) }
    Button(onClick = { showCard = true }) {
        Text(text = "Voir plus")
    }
    if (showCard) {
        showCard = showAptitudeCard(aptitude = aptitude, commentary = commentary, application = application)
    }
}

@Composable
fun showAptitudeCard(aptitude: Aptitude, commentary: String?, application: Application): Boolean {
    var showCard by remember { mutableStateOf(true) }
    val skill = getSkillById(application = application, id = aptitude.skillId)
    val level = getLevelById(application = application, id = skill!!.levelId)
    val context = LocalContext.current
    if (showCard) {
        AlertDialog(
            onDismissRequest = { showCard = false },
            title = { Text(
                text = aptitude.name,
                fontWeight = FontWeight.Bold) },
            text = { Text(text = "Level : " + level?.name)
                   if(commentary != null) {
                       Spacer(modifier = Modifier.height(16.dp))
                       Text(text = "Commentaire : " + commentary)
                   }},
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