package br.com.lno.skillmeter.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.lno.skillmeter.model.Skill

@Database(entities = [Skill::class], exportSchema = false, version = 1)
abstract class SkillDatabase : RoomDatabase() {
    abstract fun skillDao(): SkillDao
}