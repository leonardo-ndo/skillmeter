package br.com.lno.skillmeter.model.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.lno.skillmeter.model.Skill
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {

    @Insert
    fun create(skill: Skill): Long

    @Query("Select * from skills")
    fun retrieveFlow(): Flow<List<Skill>>

    @Query("Select * from skills")
    fun retrieveLiveData(): LiveData<List<Skill>>

    @Update
    fun update(skill: Skill): Int

    @Delete
    fun delete(skill: Skill): Int

}