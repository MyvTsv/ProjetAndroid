package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Status
import com.example.suballigator.repository.StatusRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StatusViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StatusRepository

    init {
        val statusDao = AppDatabase.getDatabase(application).statusDAO()
        repository = StatusRepository(statusDao)
    }

    suspend fun insert(status: Status) = repository.insert(status)

    suspend fun insert(status: List<Status>) = repository.insert(status)

    suspend fun getAll() = repository.getAll()

    suspend fun getStatusById(status_id: Int) = repository.getStatusById(status_id)

    suspend fun isExist(status: Status) = repository.isExist(status)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertStatusFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (status in api.getStatus()) {
                    insert(status)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}