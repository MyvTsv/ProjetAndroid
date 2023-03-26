package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Level
import com.example.suballigator.repository.LevelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LevelViewModel(application: Application): AndroidViewModel(application) {

    private val repository: LevelRepository

    init {
        val levelDao = AppDatabase.getDatabase(application).levelDAO()
        repository = LevelRepository(levelDao)
    }

    suspend fun insert(level: Level) = repository.insert(level)

    suspend fun insert(level: List<Level>) = repository.insert(level)

    suspend fun getAll() = repository.getAll()

    suspend fun getLevelById(id: Int) = repository.getLevelById(id)

    suspend fun isExist(level: Level) = repository.isExist(level)

    suspend fun deleteAll() = repository.deleteAll()

    suspend fun insertLevelFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        try {
            for (level in api.getLevel()) {
                insert(level)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}