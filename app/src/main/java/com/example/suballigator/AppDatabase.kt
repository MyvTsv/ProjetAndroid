package com.example.suballigator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.suballigator.dao.*
import com.example.suballigator.entity.*

@Database(entities = [Aptitude::class, ContainSkill::class, Content::class, Formation::class, Initiator::class, Level::class,
                     Participant::class, Session::class, Skill:: class, Status::class, Student::class, TrainingManager::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun aptitudeDAO(): AptitudeDAO

    abstract fun containSkillDAO(): ContainSkillDAO

    abstract fun contentDAO(): ContentDAO

    abstract fun formationDAO(): FormationDAO

    abstract fun initiatorDAO(): InitiatorDAO

    abstract fun levelDAO(): LevelDAO

    abstract fun participantDAO(): ParticipantDAO

    abstract fun sessionDAO(): SessionDAO

    abstract fun skillDAO(): SkillDAO

    abstract fun statusDAO(): StatusDAO

    abstract fun studentDAO(): StudentDAO

    abstract fun trainingManagerDAO(): TrainingManagerDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "appDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}