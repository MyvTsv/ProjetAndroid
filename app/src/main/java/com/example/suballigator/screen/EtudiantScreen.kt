package com.example.suballigator.screen

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.suballigator.entity.Student
import com.example.suballigator.showNavigation
import com.example.suballigator.viewmodel.StudentViewModel
import kotlinx.coroutines.runBlocking


@Composable
fun EtudiantScreen(application: Application, listStudent: List<Student>?) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(text = "Nom", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Text(text = "Phone", fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (listStudent != null) {
            items(listStudent.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(text = listStudent[index].name, modifier = Modifier.weight(1f))
                    Text(text = listStudent[index].phone, modifier = Modifier.weight(2f))
                }
            }
        }
    }
}