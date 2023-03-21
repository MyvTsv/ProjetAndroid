package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.TrainingManager
import com.example.suballigator.repository.TrainingManagerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrainingManagerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TrainingManagerRepository

    init {
        val trainingManagerDao = AppDatabase.getDatabase(application).trainingManagerDAO()
        repository = TrainingManagerRepository(trainingManagerDao)
    }

    suspend fun insert(trainingManager: TrainingManager) = repository.insert(trainingManager)

    suspend fun getAll() = repository.getAll()

    suspend fun isExist(trainingManager: TrainingManager) = repository.isExist(trainingManager)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertTrainingManagerFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (trainingManager in api.getTrainingManager()) {
                    insert(trainingManager)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}