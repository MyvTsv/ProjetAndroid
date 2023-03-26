package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Formation
import com.example.suballigator.repository.FormationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FormationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FormationRepository

    init {
        val formationDao = AppDatabase.getDatabase(application).formationDAO()
        repository = FormationRepository(formationDao)
    }

    suspend fun insert(formation: Formation) = repository.insert(formation)

    suspend fun insert(formation: List<Formation>) = repository.insert(formation)

    suspend fun getAll() = repository.getAll()

    suspend fun getFormationById(id: Int) = repository.getFormationById(id)

    suspend fun isExist(formation: Formation) = repository.isExist(formation)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertFormationFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (formation in api.getFormation()) {
                    insert(formation)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}