package br.com.lno.skillmeter.model.repository

import androidx.lifecycle.LiveData
import br.com.lno.skillmeter.model.Skill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SkillRepository @Inject constructor(private val skillDao: SkillDao) {

    /**
     * Inserts a [Skill] to the database.
     *
     * @param skill [Skill] object to be inserted.
     *
     * @return The id for the inserted item.
     */
    suspend fun create(skill: Skill): Long = withContext(Dispatchers.IO) {
        skillDao.create(skill)
    }

    /**
     * Retrieves all [Skill]'s from the database.
     *
     * @return A [Flow] object containing a list of [Skill]'s
     */
    fun retrieveFlow(): Flow<List<Skill>> = skillDao.retrieveFlow()

    /**
     * Retrieves all [Skill]'s from the database.
     *
     * @return A [LiveData] object containing a list of [Skill]'s
     */
    fun retrieveLiveData(): LiveData<List<Skill>> = skillDao.retrieveLiveData()

    /**
     * Updates a Skill in the database.
     *
     * @param skill [Skill] object to be inserted.
     *
     * @return Number of rows successfully updated in the table.
     */
    suspend fun update(skill: Skill): Int = withContext(Dispatchers.IO) {
        skillDao.update(skill)
    }

    /**
     * Deletes a [Skill] from the database.
     *
     * @param skill [Skill] object to be deleted.
     *
     * @return Number of rows successfully deleted in the table.
     */
    suspend fun delete(skill: Skill): Int = withContext(Dispatchers.IO) {
        skillDao.delete(skill)
    }
}