package br.com.lno.skillmeter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lno.skillmeter.model.Skill
import br.com.lno.skillmeter.model.repository.SkillRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SkillViewModel @Inject constructor(private val skillRepository: SkillRepository) :
    ViewModel() {

    val sortBy = MutableLiveData("name")

    var skills = MutableLiveData<List<Skill>>()

    /**
     * Inserts a [Skill] to the database.
     *
     * Exceptions, if any, need to be handled from the caller try/catch block.
     *
     * @param skill [Skill] object to be inserted.
     */
    fun create(skill: Skill) = runBlocking {
        skillRepository.create(skill)
    }

    /**
     * Retrieves all [Skill]'s from the database.
     *
     * @param orderBy Which order the results should follow.
     *
     * @return A [LiveData] object containing a list of [Skill]'s
     */
    fun retrieve(orderBy: String) = viewModelScope.launch {
        skills.value = if (orderBy == "level") {
            skillRepository.retrieveOrderByLevelDesc()
        } else {
            skillRepository.retrieveOrderByNameAsc()
        }
    }

    /**
     * Updates a Skill in the database.
     *
     * @param skill [Skill] object to be inserted.
     */
    fun update(skill: Skill) = runBlocking {
        skillRepository.update(skill)
    }

    /**
     * Deletes a [Skill] from the database.
     *
     * Exceptions, if any, need to be handled from the caller try/catch block.
     *
     * @param skill [Skill] object to be deleted.
     */
    fun delete(skill: Skill) = viewModelScope.launch {
        skillRepository.delete(skill)
    }

    /**
     * Sets the sort method for the list
     *
     * @param value Sort method: name or level
     */
    fun sortBy(value: String) {
        sortBy.postValue(value)
    }
}