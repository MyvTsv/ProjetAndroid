package com.example.suballigator

import android.annotation.SuppressLint
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
import com.example.suballigator.entity.Level
import com.example.suballigator.ui.theme.SubAlligatorTheme
import com.example.suballigator.viewmodel.LevelViewModel
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

                    val viewModel = LevelViewModel(application)

                    CoroutineScope(Dispatchers.IO).launch {

                        //AppDatabase.destroyInstance()
                        viewModel.insertLevelFromAPI()

                        for (level in viewModel.getAll()) {
                            println(level)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SubAlligatorTheme {
        Greeting("Androide")
    }
}