package com.example.suballigator

import ContainSkillViewModel
import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.navigation.compose.composable
import androidx.compose.runtime.*
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
               /* Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {




                    setContent(navigationMenu(application = application, navHostController))
                }*/
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
        composable(route = BottomMenuItem.etudiantScreen.route) { EtudiantScreen(application, getStudent(application)) }
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
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route)
        }
    )
}


//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun navigationMenu(application: Application, navController: NavHostController): CompositionContext? {
//    Scaffold(
//        bottomBar = {
//            BottomNavigation {
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentDestination = navBackStackEntry?.destination
//                val items = listOf(
//                    BottomMenuItem.formationScreen,
//                    BottomMenuItem.etudiantScreen,
//                    BottomMenuItem.seanceScreen,
//                    BottomMenuItem.profilScreen
//                )
//
//                items.forEach { screen ->
//                    BottomNavigationItem(
//                        icon = { Icon(screen.icon, contentDescription = null) },
//                        label = { Text(screen.title) },
//                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
//                        onClick = {
//                            navController.navigate(screen.route) {
//                                popUpTo(navController.graph.startDestinationId)
//                                launchSingleTop = true
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) {
//        Box() {
//            NavHost(
//                navController = navController,
//                startDestination = BottomMenuItem.formationScreen.route
//            ) {
//                composable(BottomMenuItem.formationScreen.route) { ProfilScreen(application) }
//                composable(BottomMenuItem.etudiantScreen.route) { EtudiantScreen(application, getStudent(application)) }
//                composable(BottomMenuItem.seanceScreen.route) { SeanceScreen(application) }
//                composable(BottomMenuItem.profilScreen.route) { ProfilScreen(application) }
//            }
//        }
//    }
//}


//@Composable
//fun navigationMenu(application: Application): CompositionContext? {
//    val pages = listOf(
//        Page("Formation", Icons.Default.Home) { FormationScreen() },
//        Page("Etudiant", Icons.Default.Person) { EtudiantScreen(application, getStudent(application = application)) },
//        Page("Seance", Icons.Default.Settings) { SeanceScreen() },
//        Page("Profil", Icons.Default.Settings) { ProfilScreen() },
//    )
//
//    var currentPage by remember { mutableStateOf(pages[0]) }
//
//    BottomNavigation {
//        pages.forEach { page ->
//            BottomNavigationItem(
//                icon = { Icon(page.icon, contentDescription = page.title) },
//                label = { Text(page.title) },
//                selected = currentPage == page,
//                onClick = { currentPage = page }
//            )
//        }
//    }
//}


@Composable
fun FormationPage() {
    Text(text = "Formation")
}


fun insertDataAPI(application: Application) {

    runBlocking {
        insertLevelAPI(application)
        insertStatusAPI(application)
    }
    sleep(500)
    runBlocking {
        insertFormationAPI(application)
        insertSkillAPI(application)
        insertInitiatorAPI(application)
    }
    sleep(500)
    runBlocking {
        insertAptitudeAPI(application)
        insertContainSkillAPI(application)
        insertSessionAPI(application)
        insertStudentAPI(application)
        insertTrainingManagerAPI(application)
    }
    sleep(500)
    runBlocking {
        insertContentAPI(application)
    }
    sleep(500)
    runBlocking {
        insertParticipantAPI(application)
    }
}

fun insertAptitudeAPI(application: Application) {
    val aptitudeViewModel = AptitudeViewModel(application)

    runBlocking {
        aptitudeViewModel.insertAptitudeFromAPI()
    }
}

fun getAptitude(application: Application): List<Aptitude>? {
    val aptitudeViewModel = AptitudeViewModel(application)
    val aptitudeData: MutableLiveData<List<Aptitude>> = MutableLiveData()

    runBlocking {
        aptitudeData.value = aptitudeViewModel.getAll()
    }

    return aptitudeData.value
}

fun insertContainSkillAPI(application: Application) {
    val containSkillViewModel = ContainSkillViewModel(application)

    runBlocking {
        containSkillViewModel.insertContainSkillFromAPI()
    }
}

fun getContainSkill(application: Application): List<ContainSkill>? {
    val containSkillViewModel = ContainSkillViewModel(application)
    val containSkillData: MutableLiveData<List<ContainSkill>> = MutableLiveData()

    runBlocking {
        containSkillData.value = containSkillViewModel.getAll()
    }

    return containSkillData.value
}

fun insertContentAPI(application: Application) {
    val contentViewModel = ContentViewModel(application)

    runBlocking {
        contentViewModel.insertContentFromAPI()
    }
}

fun getContent(application: Application): List<Content>? {
    val contentViewModel = ContentViewModel(application)
    val contentData: MutableLiveData<List<Content>> = MutableLiveData()

    runBlocking {
        contentData.value = contentViewModel.getAll()
    }

    return contentData.value
}

fun insertFormationAPI(application: Application) {
    val formationViewModel = FormationViewModel(application)

    runBlocking {
        formationViewModel.insertFormationFromAPI()
    }

}

fun getFormation(application: Application): List<Formation>? {
    val formationViewModel = FormationViewModel(application)
    val formationData: MutableLiveData<List<Formation>> = MutableLiveData()

    runBlocking {
        formationData.value = formationViewModel.getAll()
    }

    return formationData.value
}

fun insertInitiatorAPI(application: Application) {
    val initiatorViewModel = InitiatorViewModel(application)

    runBlocking {
        initiatorViewModel.insertInitiatorFromAPI()
    }
}

fun getInitiator(application: Application): List<Initiator>? {
    val initiatorViewModel = InitiatorViewModel(application)
    val initiatorData: MutableLiveData<List<Initiator>> = MutableLiveData()

    runBlocking {
        initiatorData.value = initiatorViewModel.getAll()
    }

    return initiatorData.value
}

fun insertLevelAPI(application: Application) {
    val levelViewModel = LevelViewModel(application)

    runBlocking {
        levelViewModel.insertLevelFromAPI()
    }
}

fun getLevel(application: Application): List<Level>? {
    val levelViewModel = LevelViewModel(application)
    val levelData: MutableLiveData<List<Level>> = MutableLiveData()

    runBlocking {
        levelData.value = levelViewModel.getAll()
    }

    return levelData.value
}

fun insertParticipantAPI(application: Application) {
    val participantViewModel = ParticipantViewModel(application)

    runBlocking {
        participantViewModel.insertParticipantFromAPI()
    }
}

fun getParticipant(application: Application): List<Participant>? {
    val participantViewModel = ParticipantViewModel(application)
    val participantData: MutableLiveData<List<Participant>> = MutableLiveData()

    runBlocking {
        participantData.value = participantViewModel.getAll()
    }

    return participantData.value
}

fun insertSessionAPI(application: Application) {
    val sessionViewModel = SessionViewModel(application)

    runBlocking {
        sessionViewModel.insertSessionFromAPI()
    }
}

fun getSession(application: Application): List<Session>? {
    val sessionViewModel = SessionViewModel(application)
    val sessionData: MutableLiveData<List<Session>> = MutableLiveData()

    runBlocking {
        sessionData.value = sessionViewModel.getAll()
    }

    return sessionData.value
}

fun insertSkillAPI(application: Application) {
    val skillViewModel = SkillViewModel(application)

    runBlocking {
        skillViewModel.insertSkillFromAPI()
    }
}

fun getSkill(application: Application): List<Skill>? {
    val skillViewModel = SkillViewModel(application)
    val skillData: MutableLiveData<List<Skill>> = MutableLiveData()

    runBlocking {
        skillData.value = skillViewModel.getAll()
    }

    return skillData.value
}

fun insertStatusAPI(application: Application) {
    val statusViewModel = StatusViewModel(application)

    runBlocking {
        statusViewModel.insertStatusFromAPI()
    }
}

fun getStatus(application: Application): List<Status>? {
    val statusViewModel = StatusViewModel(application)
    val statusData: MutableLiveData<List<Status>> = MutableLiveData()

    runBlocking {
        statusData.value = statusViewModel.getAll()
    }

    return statusData.value
}

fun insertStudentAPI(application: Application) {
    val studentViewModel = StudentViewModel(application)

    runBlocking {
        studentViewModel.insertStudentFromAPI()
    }
}

fun getStudent(application: Application): List<Student>? {
    val studentViewModel = StudentViewModel(application)
    val studentData: MutableLiveData<List<Student>> = MutableLiveData()

    runBlocking {
        studentData.value = studentViewModel.getAll()
    }

    return studentData.value
}

fun insertTrainingManagerAPI(application: Application) {
    val trainingManagerViewModel = TrainingManagerViewModel(application)

    runBlocking {
        trainingManagerViewModel.insertTrainingManagerFromAPI()
    }
}

fun getTrainingManager(application: Application): List<TrainingManager>? {
    val trainingManagerViewModel = TrainingManagerViewModel(application)
    val trainingManagerData: MutableLiveData<List<TrainingManager>> = MutableLiveData()

    runBlocking {
        trainingManagerData.value = trainingManagerViewModel.getAll()
    }

    return trainingManagerData.value
}

//@Composable
//fun MyBottomNavigation() {
//    val tabs = listOf("Accueil", "Profil", "Paramètres")
//    val selectedTabIndex = remember { mutableStateOf(0) }
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigation {
//                items(tabs.size) { index ->
//                    BottomNavigationItem(
//                        icon = { Icon(Icons.Default.Home, contentDescription = "Accueil") },
//                        label = { Text(tabs[index]) },
//                        selected = selectedTabIndex.value == index,
//                        onClick = { selectedTabIndex.value = index }
//                    )
//                }
//            }
//        }
//    ) {
//        when (selectedTabIndex.value) {
//            0 -> HomeScreen()
//            1 -> ProfileScreen()
//            2 -> SettingsScreen()
//        }
//    }
//}
