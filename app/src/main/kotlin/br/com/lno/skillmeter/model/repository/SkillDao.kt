package br.com.lno.skillmeter.model.repository

import androidx.room.*
import br.com.lno.skillmeter.model.Skill

@Dao
interface SkillDao {

    @Insert
    fun create(skill: Skill): Long

    @Query("Select * from skills order by name COLLATE NOCASE ASC")
    fun retrieveOrderByNameAsc(): List<Skill>

    @Query("Select * from skills order by level COLLATE NOCASE DESC")
    fun retrieveOrderByLevelDesc(): List<Skill>

    @Update
    fun update(skill: Skill): Int

    @Delete
    fun delete(skill: Skill): Int

}