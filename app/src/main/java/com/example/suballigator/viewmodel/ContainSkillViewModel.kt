import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.suballigator.APIService
import com.example.suballigator.AppDatabase
import com.example.suballigator.entity.ContainSkill
import com.example.suballigator.repository.ContainSkillRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContainSkillViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContainSkillRepository
    private val dataAPI: MutableLiveData<List<ContainSkill>> = MutableLiveData()

    init {
        val containSkillDao = AppDatabase.getDatabase(application).containSkillDAO()
        repository = ContainSkillRepository(containSkillDao)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            dataAPI.postValue(api.getContainSkill())
        }
    }

    fun getDataAPI(): LiveData<List<ContainSkill>> = dataAPI

    suspend fun insert(containSkill: ContainSkill) = repository.insert(containSkill)

    suspend fun getAll() = repository.getAll()

}
