package com.example.suballigator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuItem(val route: String, val title: String, val icon: ImageVector) {

    object formationScreen: BottomMenuItem("FormationScreen", "Formation", Icons.Default.Person)
    object etudiantScreen: BottomMenuItem("EtudiantScreen", "Etudiant", Icons.Default.Person)
    object seanceScreen: BottomMenuItem("SeanceScreen", "Seance", Icons.Default.Person)
    object profilScreen: BottomMenuItem("ProfilScreen", "Profil", Icons.Default.Person)

}