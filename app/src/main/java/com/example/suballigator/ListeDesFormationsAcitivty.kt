package com.example.suballigator

import ContainSkillViewModel
import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.navigation.compose.composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.suballigator.screen.*
import com.example.suballigator.entity.*
import com.example.suballigator.ui.theme.SubAlligatorTheme
import com.example.suballigator.viewmodel.*
import kotlinx.coroutines.*
import java.lang.Thread.sleep

class ListeDesFormationsAcitivty : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking { insertDataAPI(application) }
        setContent {
            SubAlligatorTheme {
                val navController = rememberNavController()
                Scaffold (
                    bottomBar = { BottomBar(navController = navController)}
                ) {
                    BottomNavGraph(application = application, navController = navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavGraph(application: Application, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomMenuItem.formationScreen.route
    ) {
        composable(route = BottomMenuItem.formationScreen.route) { FormationScreen(application) }
        composable(route = BottomMenuItem.etudiantScreen.route) { EtudiantScreen(application) }
        composable(route = BottomMenuItem.seanceScreen.route) { SeanceScreen(application) }
        composable(route = BottomMenuItem.profilScreen.route) { ProfilScreen(application) }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomMenuItem.formationScreen,
        BottomMenuItem.etudiantScreen,
        BottomMenuItem.seanceScreen,
        BottomMenuItem.profilScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        items.forEach { item ->
            AddItem(
                screen = item,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomMenuItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = Color(red = 0, green = 0, blue = 0),
        onClick = {
            navController.navigate(screen.route)
        },
        modifier = if (currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true) {
            Modifier.background(color = Color(red = 30, green = 144, blue = 255))
        } else {
            Modifier.background(color = Color(red = 255, green = 255, blue = 255))
        }
    )
}