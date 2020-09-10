package com.janewaitara.gadsleaderboard.ui.learnerLeaders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janewaitara.gadsleaderboard.model.data.LearningLeaders
import com.janewaitara.gadsleaderboard.repository.LeaderRepository
import com.janewaitara.gadsleaderboard.model.results.Result
import kotlinx.coroutines.launch

class LearningLeadersViewModel (private val repository: LeaderRepository ): ViewModel(){

    private val learningLeadersViewState = MutableLiveData<LearningLeadersViewState>()

    fun getLearningLeadersViewState(): LiveData<LearningLeadersViewState> = learningLeadersViewState

    fun getLearningLeaders() {
        learningLeadersViewState.value = LearningLeadersLoading

        viewModelScope.launch {
            val learningLeaders = repository.getLearnersLeader()

            learningLeadersViewState.value = LearningLeadersSuccess(learningLeaders)
        }
    }
}

sealed class LearningLeadersViewState
object LearningLeadersLoading: LearningLeadersViewState()
data class LearningLeadersSuccess(val learningLeaders: Result<List<LearningLeaders>>): LearningLeadersViewState()