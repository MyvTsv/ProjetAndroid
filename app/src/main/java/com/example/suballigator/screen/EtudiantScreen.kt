package com.example.suballigator.screen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.suballigator.entity.Student
import com.example.suballigator.getFormation
import com.example.suballigator.getStudentNoDeleted
import com.example.suballigator.viewmodel.StudentViewModel
import kotlinx.coroutines.runBlocking


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EtudiantScreen(application: Application) {
    Scaffold(
        topBar = { TopBar() },
        backgroundColor = Color(red = 0xF9, green = 0xF9, blue = 0xF9),
        content = {
            val dataHashMap = LinkedHashMap<String, List<String>>()
            val dataStudentName = mutableListOf<String>()
            val dataStudentPhone = mutableListOf<String>()

            getStudentNoDeleted(application = application)?.forEach { dataStudentName.add(it.name) }
            getStudentNoDeleted(application = application)?.forEach { dataStudentPhone.add(it.phone) }

            dataHashMap.put("Nom", dataStudentName)
            dataHashMap.put("Phone", dataStudentPhone)

            CreateList(dataTab = dataHashMap)
        }
    )
}