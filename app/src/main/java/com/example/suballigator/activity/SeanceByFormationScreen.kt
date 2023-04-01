package com.example.suballigator.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.suballigator.activity.ui.theme.SubAlligatorTheme
import com.example.suballigator.getFormationById
import com.example.suballigator.getSession
import com.example.suballigator.getSessionByFormationId
import com.example.suballigator.screen.CreateSessionList
import com.example.suballigator.screen.TopBar

class SeanceByFormationScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var idFormation = intent.getIntExtra("formationId", 1)
        var formation = getFormationById(application = application, id = idFormation)
        setContent {
            SubAlligatorTheme {

                Scaffold(
                    topBar = { TopBar(application, "Séance pour la formation " + formation.name) },
                    content = { CreateSessionList(sessions = getSessionByFormationId(application = application, id = idFormation))
                    }
                )
            }
        }
    }
}