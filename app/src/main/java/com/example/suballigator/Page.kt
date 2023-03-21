package com.example.suballigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class Page(
    val title: String,
    val icon: ImageVector,
    val screenContent: @Composable () -> Unit
)
