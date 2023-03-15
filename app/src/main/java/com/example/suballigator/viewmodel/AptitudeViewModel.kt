package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Aptitude
import com.example.suballigator.repository.AptitudeRepository

class AptitudeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AptitudeRepository

    init {
        val aptitudeDao = AppDatabase.getDatabase(application).aptitudeDAO()
        repository = AptitudeRepository(aptitudeDao)
    }

    suspend fun insert(aptitude: Aptitude) = repository.insert(aptitude)

    suspend fun getAll() = repository.getAll()

}