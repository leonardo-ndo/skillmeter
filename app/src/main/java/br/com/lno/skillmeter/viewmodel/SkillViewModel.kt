package br.com.lno.skillmeter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.lno.skillmeter.model.Skill
import br.com.lno.skillmeter.model.repository.SkillRepository
import kotlinx.coroutines.*

class SkillViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Inserts a [Skill] to the database.
     *
     * Exceptions, if any, need to be handled from the caller try/catch block.
     *
     * @param skill [Skill] object to be inserted.
     */
    fun create(skill: Skill) {
        runBlocking {
            val job = CoroutineScope(Dispatchers.IO).async {
                SkillRepository.create(getApplication(), skill)
            }
            job.await()
        }
    }

    /**
     * Retrieves all [Skill]'s from the database.
     *
     * @return A [LiveData] object containing a list of [Skill]'s
     */
    fun retrieve(): LiveData<List<Skill>> {
        return SkillRepository.retrieve(getApplication())
    }

    /**
     * Updates a Skill in the database.
     *
     * Exceptions are handled through the [CoroutineExceptionHandler] object. This is not very
     * good in this case, because we can't handle the finish activity behavior.
     *
     * @param skill [Skill] object to be inserted.
     * @param exceptionHandler [CoroutineExceptionHandler] object, which will handle any exceptions.
     */
    fun update(skill: Skill, exceptionHandler: CoroutineExceptionHandler) {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            SkillRepository.update(getApplication(), skill)
        }
    }

    /**
     * Deletes a [Skill] from the database.
     *
     * Exceptions, if any, need to be handled from the caller try/catch block.
     *
     * @param skill [Skill] object to be deleted.
     */
    fun delete(skill: Skill) {
        runBlocking {
            val job = CoroutineScope(Dispatchers.IO).async {
                SkillRepository.delete(getApplication(), skill)
            }
            job.await()
        }
    }
}