package com.example.suballigator

import ContainSkillViewModel
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.suballigator.entity.*
import com.example.suballigator.viewmodel.*
import kotlinx.coroutines.runBlocking


fun insertDataAPI(application: Application) {

    runBlocking {
        //println("DEBUT INSERTION")
        insertLevelAPI(application)
        //println("Level INSERT")
        insertStatusAPI(application)
        //println("Status INSERT")
    }
    Thread.sleep(500)
    runBlocking {
        insertFormationAPI(application)
        //println("Fomration INSERT")
        insertSkillAPI(application)
        //println("Skill INSERT")
        insertInitiatorAPI(application)
        //println("Initiator INSERT")
    }
    Thread.sleep(500)
    runBlocking {
        insertAptitudeAPI(application)
        //println("Aptitude INSERT")
        insertContainSkillAPI(application)
        //println("ContainSkill INSERT")
        insertSessionAPI(application)
        //println("Session INSERT")
        insertStudentAPI(application)
        //println("Student INSERT")
        insertTrainingManagerAPI(application)
        //println("TrainingManager INSERT")
    }
    Thread.sleep(500)
    runBlocking {
        insertContentAPI(application)
        //println("Content INSERT")
    }
    Thread.sleep(500)
    runBlocking {
        insertParticipantAPI(application)
        //println("Participant INSERT")
    }
}

fun insertAptitudeAPI(application: Application) {
    val aptitudeViewModel = AptitudeViewModel(application)

    runBlocking {
        aptitudeViewModel.insertAptitudeFromAPI()
    }
}

fun getAptitude(application: Application): List<Aptitude>? {
    val aptitudeViewModel = AptitudeViewModel(application)
    val aptitudeData: MutableLiveData<List<Aptitude>> = MutableLiveData()

    runBlocking {
        aptitudeData.value = aptitudeViewModel.getAll()
    }

    return aptitudeData.value
}

fun getAptitudeById(application: Application, id: Int): Aptitude {
    val aptitudeViewModel = AptitudeViewModel(application)
    val aptitudeData: Aptitude

    runBlocking {
        aptitudeData = aptitudeViewModel.getAptitudeById(id)
    }

    return aptitudeData
}

fun insertContainSkillAPI(application: Application) {
    val containSkillViewModel = ContainSkillViewModel(application)

    runBlocking {
        containSkillViewModel.insertContainSkillFromAPI()
    }
}

fun getContainSkill(application: Application): List<ContainSkill>? {
    val containSkillViewModel = ContainSkillViewModel(application)
    val containSkillData: MutableLiveData<List<ContainSkill>> = MutableLiveData()

    runBlocking {
        containSkillData.value = containSkillViewModel.getAll()
    }

    return containSkillData.value
}

fun insertContentAPI(application: Application) {
    val contentViewModel = ContentViewModel(application)

    runBlocking {
        contentViewModel.insertContentFromAPI()
    }
}

fun getContent(application: Application): List<Content>? {
    val contentViewModel = ContentViewModel(application)
    val contentData: MutableLiveData<List<Content>> = MutableLiveData()

    runBlocking {
        contentData.value = contentViewModel.getAll()
    }

    return contentData.value
}

fun getContentById(application: Application, id: Int): Content {
    val contentViewModel = ContentViewModel(application)
    val contentData: Content

    runBlocking {
        contentData = contentViewModel.getContentById(id)
    }

    return contentData
}

fun getContentBySessionId(application: Application, id: Int): List<Content>? {
    val contentViewModel = ContentViewModel(application)
    val contentData: MutableLiveData<List<Content>> = MutableLiveData()

    runBlocking {
        contentData.value = contentViewModel.getContentBySessionId(id)
    }

    return contentData.value
}

fun insertFormationAPI(application: Application) {
    val formationViewModel = FormationViewModel(application)

    runBlocking {
        formationViewModel.insertFormationFromAPI()
    }

}

fun getFormation(application: Application): List<Formation>? {
    val formationViewModel = FormationViewModel(application)
    val formationData: MutableLiveData<List<Formation>> = MutableLiveData()

    runBlocking {
        formationData.value = formationViewModel.getAll()
    }

    return formationData.value
}

fun getFormationById(application: Application, id: Int): Formation {
    val formationViewModel = FormationViewModel(application)
    val formationData: Formation

    runBlocking {
        formationData = formationViewModel.getFormationById(id)
    }

    return formationData
}

fun insertInitiatorAPI(application: Application) {
    val initiatorViewModel = InitiatorViewModel(application)

    runBlocking {
        initiatorViewModel.insertInitiatorFromAPI()
    }
}

fun getInitiator(application: Application): List<Initiator>? {
    val initiatorViewModel = InitiatorViewModel(application)
    val initiatorData: MutableLiveData<List<Initiator>> = MutableLiveData()

    runBlocking {
        initiatorData.value = initiatorViewModel.getAll()
    }

    return initiatorData.value
}

fun insertLevelAPI(application: Application) {
    val levelViewModel = LevelViewModel(application)

    runBlocking {
        levelViewModel.insertLevelFromAPI()
    }
}

fun getLevel(application: Application): List<Level>? {
    val levelViewModel = LevelViewModel(application)
    val levelData: MutableLiveData<List<Level>> = MutableLiveData()

    runBlocking {
        levelData.value = levelViewModel.getAll()
    }

    return levelData.value
}

fun getLevelById(application: Application, id: Int): Level? {
    val levelViewModel = LevelViewModel(application)
    val levelData: Level

    runBlocking {
        levelData = levelViewModel.getLevelById(id)
    }

    return levelData
}

fun insertParticipantAPI(application: Application) {
    val participantViewModel = ParticipantViewModel(application)

    runBlocking {
        participantViewModel.insertParticipantFromAPI()
    }
}

fun getParticipant(application: Application): List<Participant>? {
    val participantViewModel = ParticipantViewModel(application)
    val participantData: MutableLiveData<List<Participant>> = MutableLiveData()

    runBlocking {
        participantData.value = participantViewModel.getAll()
    }

    return participantData.value
}

fun getParticipantById(application: Application, id: Int): Participant? {
    val participantViewModel = ParticipantViewModel(application)
    val participantData: Participant

    runBlocking {
        participantData = participantViewModel.getParticipantById(id)
    }

    return participantData
}

fun getParticipantByStudentId(application: Application, id: Int): List<Participant>? {
    val participantViewModel = ParticipantViewModel(application)
    val participantData: MutableLiveData<List<Participant>> = MutableLiveData()

    runBlocking {
        participantData.value = participantViewModel.getParticipantByStudentId(id)
    }

    return participantData.value
}

fun insertSessionAPI(application: Application) {
    val sessionViewModel = SessionViewModel(application)

    runBlocking {
        sessionViewModel.insertSessionFromAPI()
    }
}

fun getSession(application: Application): List<Session>? {
    val sessionViewModel = SessionViewModel(application)
    val sessionData: MutableLiveData<List<Session>> = MutableLiveData()

    runBlocking {
        sessionData.value = sessionViewModel.getAll()
    }

    return sessionData.value
}

fun getSessionById(application: Application, id: Int): Session? {
    val sessionViewModel = SessionViewModel(application)
    val sessionData: Session

    runBlocking {
        sessionData = sessionViewModel.getSessionbyId(id)
    }

    return sessionData
}

fun getSessionByFormationId(application: Application, id: Int): List<Session>? {
    val sessionViewModel = SessionViewModel(application)
    val sessionData: List<Session>

    runBlocking {
        sessionData = sessionViewModel.getSessionByFromationId(id)
    }

    return sessionData
}

fun insertSkillAPI(application: Application) {
    val skillViewModel = SkillViewModel(application)

    runBlocking {
        skillViewModel.insertSkillFromAPI()
    }
}

fun getSkill(application: Application): List<Skill>? {
    val skillViewModel = SkillViewModel(application)
    val skillData: MutableLiveData<List<Skill>> = MutableLiveData()

    runBlocking {
        skillData.value = skillViewModel.getAll()
    }

    return skillData.value
}

fun getSkillById(application: Application, id: Int): Skill? {
    val skillViewModel = SkillViewModel(application)
    val skillData: Skill

    runBlocking {
        skillData = skillViewModel.getSkillById(id)
    }

    return skillData
}

fun insertStatusAPI(application: Application) {
    val statusViewModel = StatusViewModel(application)

    runBlocking {
        statusViewModel.insertStatusFromAPI()
    }
}

fun getStatus(application: Application): List<Status>? {
    val statusViewModel = StatusViewModel(application)
    val statusData: MutableLiveData<List<Status>> = MutableLiveData()

    runBlocking {
        statusData.value = statusViewModel.getAll()
    }

    return statusData.value
}

fun getStatusById(application: Application, id: Int): Status? {
    val statusViewModel = StatusViewModel(application)
    val statusData: Status

    runBlocking {
        statusData = statusViewModel.getStatusById(id)
    }

    return statusData
}

fun insertStudentAPI(application: Application) {
    val studentViewModel = StudentViewModel(application)

    runBlocking {
        studentViewModel.insertStudentFromAPI()
    }
}

fun getStudent(application: Application): List<Student>? {
    val studentViewModel = StudentViewModel(application)
    val studentData: MutableLiveData<List<Student>> = MutableLiveData()

    runBlocking {
        studentData.value = studentViewModel.getAll()
    }

    return studentData.value
}

fun getStudentNoDeleted(application: Application): List<Student>? {
    val studentViewModel = StudentViewModel(application)
    val studentData: MutableLiveData<List<Student>> = MutableLiveData()

    runBlocking {
        studentData.value = studentViewModel.getAllNoDeleted()
    }

    studentData.value.toString()

    return studentData.value
}

fun getStudentByFormationId(application: Application, id: Int): Student? {
    val studentViewModel = StudentViewModel(application)
    val studentData: Student

    runBlocking {
        studentData = studentViewModel.getStudentByFormationId(id)
    }

    return studentData
}


fun getStudentById(application: Application, id: Int): Student? {
    val studentViewModel = StudentViewModel(application)
    val studentData: Student

    runBlocking {
        studentData = studentViewModel.getStudentById(id)
    }

    return studentData
}
fun insertTrainingManagerAPI(application: Application) {
    val trainingManagerViewModel = TrainingManagerViewModel(application)

    runBlocking {
        trainingManagerViewModel.insertTrainingManagerFromAPI()
    }
}

fun getTrainingManager(application: Application): List<TrainingManager>? {
    val trainingManagerViewModel = TrainingManagerViewModel(application)
    val trainingManagerData: MutableLiveData<List<TrainingManager>> = MutableLiveData()

    runBlocking {
        trainingManagerData.value = trainingManagerViewModel.getAll()
    }

    return trainingManagerData.value
}