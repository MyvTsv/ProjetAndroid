package com.example.suballigator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuItem(val route: String, val title: String, val icon: ImageVector) {

    object formationScreen: BottomMenuItem("FormationScreen", "FormationScreen", Icons.Default.Person)
    object etudiantScreen: BottomMenuItem("EtudiantScreen", "EtudiantScreen", Icons.Default.Person)
    object seanceScreen: BottomMenuItem("SeanceScreen", "SeanceScreen", Icons.Default.Person)
    object profilScreen: BottomMenuItem("ProfilScreen", "ProfilScreen", Icons.Default.Person)

}