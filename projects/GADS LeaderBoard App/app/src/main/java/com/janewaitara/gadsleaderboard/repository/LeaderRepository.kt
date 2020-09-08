package com.janewaitara.gadsleaderboard.repository

import com.janewaitara.gadsleaderboard.networking.RemoteApi

class LeaderRepository(private val remoteApi: RemoteApi) {
    
    suspend fun getLearnersLeader() = remoteApi.getLearnersLeaders()
    suspend fun getSkillIQLeader() = remoteApi.getSkillIQLeaders()
}