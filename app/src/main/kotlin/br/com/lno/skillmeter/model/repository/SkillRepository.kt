package br.com.lno.skillmeter.model.repository

import androidx.lifecycle.LiveData
import br.com.lno.skillmeter.model.Skill
import kotlinx.coroutines.Dispatchers
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
     * Retrieves all [Skill]'s from the database, ordering the results by name ASC
     *
     * @return A [LiveData] object containing a list of [Skill]'s
     */
    suspend fun retrieveOrderByLevelDesc(): List<Skill> = withContext(Dispatchers.IO) {
        skillDao.retrieveOrderByLevelDesc()
    }

    /**
     * Retrieves all [Skill]'s from the database, ordering the results by level DESC.
     *
     * @return A [LiveData] object containing a list of [Skill]'s
     */
    suspend fun retrieveOrderByNameAsc(): List<Skill> = withContext(Dispatchers.IO) {
        skillDao.retrieveOrderByNameAsc()
    }

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