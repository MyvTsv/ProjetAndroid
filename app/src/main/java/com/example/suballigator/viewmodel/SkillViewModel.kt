package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Skill
import com.example.suballigator.repository.SkillRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SkillViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SkillRepository

    init {
        val skillDao = AppDatabase.getDatabase(application).skillDAO()
        repository = SkillRepository(skillDao)
    }

    suspend fun insert(skill: Skill) = repository.insert(skill)

    suspend fun getAll() = repository.getAll()

    suspend fun getSkillById(skillId: Int) = repository.getSkillById(skillId)

    suspend fun isExist(skill: Skill) = repository.isExist(skill)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertSkillFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (skill in api.getSkill()) {
                    insert(skill)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}