package com.example.suballigator

import ContainSkillViewModel
import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.suballigator.screen.*
import com.example.suballigator.entity.*
import com.example.suballigator.ui.theme.SubAlligatorTheme
import com.example.suballigator.viewmodel.*
import kotlinx.coroutines.*
import java.lang.Thread.sleep

class ListeDesFormationsAcitivty : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SubAlligatorTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    runBlocking { insertDataAPI(application) }

                    val navHostController = rememberNavController()
                    setContent(navigationMenu(application = application))

                    showApplication(application = application).screenContent()
                }
            }
        }
    }
}

//@Composable
//fun showApplication(application: Application): Page {
//    return showNavigation(application = application)
//}

@Composable
fun navigationMenu(application: Application): CompositionContext? {
    val pages = listOf(
        Page("Formation", Icons.Default.Home) { FormationScreen() },
        Page("Etudiant", Icons.Default.Person) { EtudiantScreen(application, getStudent(application = application)) },
        Page("Seance", Icons.Default.Settings) { SeanceScreen() },
        Page("Profil", Icons.Default.Settings) { ProfilScreen() },
    )

    var currentPage by remember { mutableStateOf(pages[0]) }

    BottomNavigation {
        pages.forEach { page ->
            BottomNavigationItem(
                icon = { Icon(page.icon, contentDescription = page.title) },
                label = { Text(page.title) },
                selected = currentPage == page,
                onClick = { currentPage = page }
            )
        }
    }
}

//    BottomNavigation(
//        modifier = Modifier.height(56.dp)
//    ) {
//        BottomNavigationItem(
//            selected = true,
//            onClick = { println("formation") },
//            icon = { Icon(Icons.Default.Home, contentDescription = "Formation") },
//            label = { Text("Formation") }
//        )
//        BottomNavigationItem(
//            selected = false,
//            onClick = {println("etudiant")},
//            icon = { Icon(Icons.Default.Search, contentDescription = "Etudiant") },
//            label = { Text("Etudiant") }
//        )
//        BottomNavigationItem(
//            selected = false,
//            onClick = {println("Seance")},
//            icon = { },
//            label = { Text("Séance") }
//        )
//        BottomNavigationItem(
//            selected = false,
//            onClick = {println("description")},
//            icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
//            label = { Text("Profil") }

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
