package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.ContainSkill
import com.example.suballigator.repository.AptitudeRepository
import com.example.suballigator.repository.ContainSkillRepository

class ContainSkillViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContainSkillRepository

    init {
        val containSkillDao = AppDatabase.getDatabase(application).containSkillDAO()
        repository = ContainSkillRepository(containSkillDao)
    }

    suspend fun insert(containSkill: ContainSkill) = repository.insert(containSkill)

    suspend fun getAll() = repository.getAll()

}