package com.example.suballigator

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
import com.example.suballigator.entity.Formation
import com.example.suballigator.entity.Level
import com.example.suballigator.entity.Status
import com.example.suballigator.ui.theme.SubAlligatorTheme
import com.example.suballigator.viewmodel.FormationViewModel
import com.example.suballigator.viewmodel.LevelViewModel
import com.example.suballigator.viewmodel.StatusViewModel
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

                    insertDataAPI(application)
                    val levelFormation: List<Status>? = getStatus(application)

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
    runBlocking {
        insertFormationAPI(application)
    }
}

fun insertLevelAPI(application: Application) {
    val levelViewModel = LevelViewModel(application)

    runBlocking {
        levelViewModel.insertLevelFromAPI()
    }
}

fun insertStatusAPI(application: Application) {
    val statusViewModel = StatusViewModel(application)

    runBlocking {
        statusViewModel.insertStatusFromAPI()
    }
}

fun insertFormationAPI(application: Application) {
    val formationViewModel = FormationViewModel(application)

    runBlocking {
        formationViewModel.insertFormationFromAPI()
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

fun getStatus(application: Application): List<Status>? {
    val statusViewModel = StatusViewModel(application)
    val statusData: MutableLiveData<List<Status>> = MutableLiveData()

    runBlocking {
        statusData.value = statusViewModel.getAll()
    }

    return statusData.value
}

fun getFormation(application: Application): List<Formation>? {
    val formationViewModel = FormationViewModel(application)
    val formationData: MutableLiveData<List<Formation>> = MutableLiveData()

    runBlocking {
        formationData.value = formationViewModel.getAll()
    }

    return formationData.value
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SubAlligatorTheme {
        Greeting("Androide")
    }
}