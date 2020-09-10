package com.janewaitara.gadsleaderboard.ui.skillIQLeaders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janewaitara.gadsleaderboard.model.data.SkillIQLeaders
import com.janewaitara.gadsleaderboard.repository.LeaderRepository
import com.janewaitara.gadsleaderboard.model.results.Result
import kotlinx.coroutines.launch

class SkillIQLeadersViewModel(private val repository: LeaderRepository): ViewModel(){

    private val skillIQLeadersViewState = MutableLiveData<SkillIQLeadersViewState>()

    fun getSkillIQLeadersViewState(): LiveData<SkillIQLeadersViewState> = skillIQLeadersViewState

    fun getSkillIQLeaders(){
        skillIQLeadersViewState.value = SkillIQLeadersLoading

        viewModelScope.launch {
            val skillIQLeaders = repository.getSkillIQLeader()

            skillIQLeadersViewState.value = SkillLeadersSuccess(skillIQLeaders)
        }
    }

}

sealed class SkillIQLeadersViewState
object SkillIQLeadersLoading: SkillIQLeadersViewState()
data class SkillLeadersSuccess(val skillIQLeaders: Result<List<SkillIQLeaders>>): SkillIQLeadersViewState()