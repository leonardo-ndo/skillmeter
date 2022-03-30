package br.com.lno.skillmeter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lno.skillmeter.model.Skill
import br.com.lno.skillmeter.model.repository.SkillRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillViewModel @Inject constructor(private val skillRepository: SkillRepository) :
    ViewModel() {

    private val _result = MutableLiveData<SkillResult>()
    val result: LiveData<SkillResult> = _result

    /**
     * Inserts a [Skill] to the database.
     *
     * Exceptions, if any, need to be handled from the caller try/catch block.
     *
     * @param skill [Skill] object to be inserted.
     */
    fun create(skill: Skill) = viewModelScope.launch {
        try {
            _result.postValue(SkillResult.Success(skillRepository.create(skill)))
        } catch (e: Exception) {
            _result.postValue(SkillResult.Error(e))
        }
    }

    /**
     * Retrieves all [Skill]'s from the database.
     *
     * @return A [Flow] object containing a list of [Skill]'s
     */
    fun skillsFlow(): Flow<List<Skill>> {
        return skillRepository.retrieveFlow()
    }

    /**
     * Retrieves all [Skill]'s from the database.
     *
     * @return A [LiveData] object containing a list of [Skill]'s
     */
    fun skillsLiveData(): LiveData<List<Skill>> {
        return skillRepository.retrieveLiveData()
    }

    /**
     * Updates a Skill in the database.
     *
     * @param skill [Skill] object to be inserted.
     */
    fun update(skill: Skill) = viewModelScope.launch {
        try {
            _result.postValue(SkillResult.Success(skillRepository.update(skill)))
        } catch (e: Exception) {
            _result.postValue(SkillResult.Error(e))
        }
    }

    /**
     * Deletes a [Skill] from the database.
     *
     * Exceptions, if any, need to be handled from the caller try/catch block.
     *
     * @param skill [Skill] object to be deleted.
     */
    fun delete(skill: Skill) = viewModelScope.launch {
        try {
            _result.postValue(SkillResult.Success(skillRepository.delete(skill)))
        } catch (e: Exception) {
            _result.postValue(SkillResult.Error(e))
        }
    }

    sealed class SkillResult {
        data class Success(val result: Number) : SkillResult()
        data class Error(val exception: Exception) : SkillResult()
    }
}