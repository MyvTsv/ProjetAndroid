package com.example.suballigator

import ContainSkillViewModel
import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.suballigator.entity.*
import com.example.suballigator.ui.theme.SubAlligatorTheme
import com.example.suballigator.viewmodel.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

                    val levelFormation: List<Participant>? = getParticipant(application)

                    Greeting(levelFormation.toString())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Text(text = "Hello $name!")
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SubAlligatorTheme {
        Greeting("Androide")
    }
}