package com.janewaitara.gadsleaderboard.networking

import com.janewaitara.gadsleaderboard.model.data.LearningLeaders
import com.janewaitara.gadsleaderboard.model.data.SkillIQLeaders
import retrofit2.http.GET

/**
 * Represent the API calls you can make
 * */

interface RemoteApiService {

    //get learning leaders
    @GET("/api/hours")
    suspend fun getLearningLeaders(): List<LearningLeaders>

    //get Skill IQ leaders
    @GET("/api/skilliq")
    suspend fun getSkillIQLeaders(): List<SkillIQLeaders>

}