package com.example.suballigator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
        private const val DATABASE_NAME = "databaseTestttttttttt"
        private val migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE formation ADD COLUMN description TEXT")
            }
        }
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance!= null) {
                return instance as AppDatabase
            }
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).addMigrations(migration)
                .build()
            return instance as AppDatabase
        }

        fun destroyInstance() {
            instance = null
        }

        fun onCreate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS formation (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT)")
        }


    }
}