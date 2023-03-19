package com.example.suballigator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.Content
import com.example.suballigator.repository.ContentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContentRepository

    init {
        val contentDao = AppDatabase.getDatabase(application).contentDAO()
        repository = ContentRepository(contentDao)
    }

    suspend fun insert(content: Content) = repository.insert(content)

    suspend fun insert(content: List<Content>) = repository.insert(content)

    suspend fun getAll() = repository.getAll()

    suspend fun isExist(content: Content) = repository.isExist(content)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertContentFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (content in api.getContent()) {
                    if (!isExist(content)) {
                        insert(content)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}