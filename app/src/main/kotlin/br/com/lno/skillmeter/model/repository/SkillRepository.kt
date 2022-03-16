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
     */
    suspend fun create(skill: Skill) = withContext(Dispatchers.IO) {
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
     */
    suspend fun update(skill: Skill) = withContext(Dispatchers.IO) {
        skillDao.update(skill)
    }

    /**
     * Deletes a [Skill] from the database.
     *
     * @param skill [Skill] object to be deleted.
     */
    suspend fun delete(skill: Skill) = withContext(Dispatchers.IO) {
        skillDao.delete(skill)
    }
}