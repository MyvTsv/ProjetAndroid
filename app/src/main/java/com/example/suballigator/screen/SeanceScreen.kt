package com.example.suballigator.screen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SeanceScreen(application: Application) {
    Scaffold(
        topBar = { TopBar(application) }
    ) {
        val dataHashMap = LinkedHashMap<String, List<String>>()
        val dataSessionDate = mutableListOf<String>()

        getSession(application = application)?.forEach { dataSessionDate.add(it.date) }

        dataHashMap.put("Date", dataSessionDate)

        CreateList(dataTab = dataHashMap)
    }
}
