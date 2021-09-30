package br.com.lno.skillmeter.model.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.lno.skillmeter.model.Skill

@Dao
interface SkillDao {

    @Insert
    fun create(skill: Skill)

    @Query("Select * from skills order by name")
    fun retrieve(): LiveData<List<Skill>>

    @Update
    fun update(skill: Skill)

    @Delete
    fun delete(skill: Skill)

}