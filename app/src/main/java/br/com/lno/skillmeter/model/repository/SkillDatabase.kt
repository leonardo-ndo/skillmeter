package br.com.lno.skillmeter.model.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.lno.skillmeter.model.Skill

@Database(entities = [Skill::class], exportSchema = false, version = 1)
abstract class SkillDatabase : RoomDatabase() {

    abstract fun skillDao(): SkillDao

    companion object {

        @Volatile
        private var instance: SkillDatabase? = null

        /**
         * Gets a [SkillDatabase] instance. Creates one if instance is null.
         *
         * @return [SkillDatabase] object.
         */
        fun getDatabase(context: Context): SkillDatabase {

            return instance ?: synchronized(this) {
                createDatabase(context).also {
                    instance = it
                }
            }
        }

        /**
         * Creates a [SkillDatabase] instance
         */
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context, SkillDatabase::class.java, "SKILL_DATABASE.db")
                .fallbackToDestructiveMigration().build()
    }
}