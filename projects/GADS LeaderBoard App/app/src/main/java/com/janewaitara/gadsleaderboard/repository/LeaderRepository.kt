package com.janewaitara.gadsleaderboard.repository

import com.janewaitara.gadsleaderboard.networking.GetRemoteApi

class LeaderRepository(private val getRemoteApi: GetRemoteApi) {
    
    suspend fun getLearnersLeader() = getRemoteApi.getLearnersLeaders()
    suspend fun getSkillIQLeader() = getRemoteApi.getSkillIQLeaders()
}