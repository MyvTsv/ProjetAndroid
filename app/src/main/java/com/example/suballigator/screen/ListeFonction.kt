package com.example.suballigator.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.suballigator.entity.Formation
import java.util.Objects

@Composable
fun AddHeadRow(title: List<String>) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color(red = 135, green = 206, blue = 250))
            .padding(vertical = 20.dp, horizontal = 1.dp),
    ) {
        title.forEach {
            AddHeadCell(it)
        }
        AddDetailHeadCell()
    }
}

@Composable
fun RowScope.AddHeadCell(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun RowScope.AddDetailHeadCell() {
    Text(
        text = "Détail",
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}