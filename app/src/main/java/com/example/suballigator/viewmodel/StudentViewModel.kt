package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Student
import com.example.suballigator.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StudentRepository

    init {
        val studentDao = AppDatabase.getDatabase(application).studentDAO()
        repository = StudentRepository(studentDao)
    }

    suspend fun insert(student: Student) = repository.insert(student)

    suspend fun getAll() = repository.getAll()

    suspend fun isExist(student: Student) = repository.isExist(student)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertStudentFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (student in api.getStudent()) {
                    if (!isExist(student)) {
                        insert(student)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}