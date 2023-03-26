package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Participant
import com.example.suballigator.repository.ParticipantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ParticipantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ParticipantRepository

    init {
        val participantDao = AppDatabase.getDatabase(application).participantDAO()
        repository = ParticipantRepository(participantDao)
    }

    suspend fun insert(participant: Participant) = repository.insert(participant)

    suspend fun getAll() = repository.getAll()

    suspend fun getParticipantById(participant_id: Int) = repository.getParticipantById(participant_id)

    suspend fun getParticipantByStudentId(student_id: Int) = repository.getParticipantByStudentId(student_id)

    suspend fun isExist(participant: Participant) = repository.isExist(participant)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertParticipantFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (participant in api.getParticipant()) {
                    insert(participant)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}