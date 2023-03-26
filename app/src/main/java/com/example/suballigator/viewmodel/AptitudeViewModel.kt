package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Aptitude
import com.example.suballigator.repository.AptitudeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AptitudeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AptitudeRepository

    init {
        val aptitudeDao = AppDatabase.getDatabase(application).aptitudeDAO()
        repository = AptitudeRepository(aptitudeDao)
    }

    suspend fun insert(aptitude: Aptitude) = repository.insert(aptitude)

    suspend fun getAll() = repository.getAll()

    suspend fun getAptitudeById(aptitudeId: Int) = repository.getAptitudeById(aptitudeId)

    suspend fun isExist(aptitude: Aptitude) = repository.isExist(aptitude)

    suspend fun deleteAll() = repository.deleteAll()


    fun insertAptitudeFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (aptitude in api.getAptitude()) {
                    insert(aptitude)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}