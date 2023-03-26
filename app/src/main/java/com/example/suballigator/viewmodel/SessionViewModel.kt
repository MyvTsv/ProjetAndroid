package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Session
import com.example.suballigator.repository.SessionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SessionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SessionRepository

    init {
        val sessionDao = AppDatabase.getDatabase(application).sessionDAO()
        repository = SessionRepository(sessionDao)
    }

    suspend fun insert(session: Session) = repository.insert(session)

    suspend fun getAll() = repository.getAll()

    suspend fun getSessionbyId(id: Int) = repository.getSessionById(id)

    suspend fun getSessionByFromationId(id: Int) = repository.getSessionByFormationId(id)

    suspend fun isExist(session: Session) = repository.isExist(session)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertSessionFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (session in api.getSession()) {
                    insert(session)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}