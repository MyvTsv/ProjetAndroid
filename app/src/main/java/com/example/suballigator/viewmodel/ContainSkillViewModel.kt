import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.ContainSkill
import com.example.suballigator.repository.ContainSkillRepository
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Thread.sleep

class ContainSkillViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContainSkillRepository

    init {
        val containSkillDao = AppDatabase.getDatabase(application).containSkillDAO()
        repository = ContainSkillRepository(containSkillDao)
    }

    suspend fun insert(containSkill: ContainSkill) = repository.insert(containSkill)

    suspend fun insert(containSkill: List<ContainSkill>) = repository.insert(containSkill)

    suspend fun getAll() = repository.getAll()

    suspend fun isExist(containSkill: ContainSkill) = repository.isExist(containSkill)

    suspend fun deleteAll() = repository.deleteAll()

    fun insertContainSkillFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        val launch = CoroutineScope(Dispatchers.IO).launch {

            try {
                for (containSkill in api.getContainSkill()) {
                    insert(containSkill)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}
