package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Initiator
import com.example.suballigator.repository.InitiatorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InitiatorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InitiatorRepository

    init {
        val initiatorDao = AppDatabase.getDatabase(application).initiatorDAO()
        repository = InitiatorRepository(initiatorDao)
    }

    suspend fun insert(initiator: Initiator) = repository.insert(initiator)

    suspend fun getAll() = repository.getAll()

    suspend fun isExist(initiator: Initiator) = repository.isExist(initiator)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertInitiatorFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (initiator in api.getInitiator()) {
                     insert(initiator)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}