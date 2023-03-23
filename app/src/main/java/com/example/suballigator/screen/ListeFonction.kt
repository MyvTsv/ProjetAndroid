package com.example.suballigator.screen

import androidx.compose.foundation.background
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
import java.util.Objects


@Composable
fun CreateList(dataTab: LinkedHashMap<String, List<String>>) {
    val dataRow = mutableListOf<String>()
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item { AddHeadRow(dataTab.keys) }
        dataTab.values.firstOrNull()?.size?.let {
            items(it) { index ->
                dataRow.clear()
                for(data in dataTab.values) {
                    dataRow.add(data[index])
                }
                AddDataRow(data = dataRow, index = index)
            }
        }
    }
}

@Composable
fun AddHeadRow(title: MutableSet<String>) {
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
fun AddDataRow(data: List<String>, index: Int) {
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
        data.forEach {
            AddCell(it)
        }
    }
}

@Composable
fun RowScope.AddCell(txt: String) {
    Text(
        text = txt,
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .wrapContentHeight(),
        textAlign = TextAlign.Center
    )
}
