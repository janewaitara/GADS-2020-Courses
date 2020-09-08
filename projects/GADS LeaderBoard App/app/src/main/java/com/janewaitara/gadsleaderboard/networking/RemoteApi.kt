package com.janewaitara.gadsleaderboard.networking

import android.util.Log
import com.janewaitara.gadsleaderboard.model.results.Success
import com.janewaitara.gadsleaderboard.model.data.LearningLeaders
import com.janewaitara.gadsleaderboard.model.data.SkillIQLeaders
import com.janewaitara.gadsleaderboard.model.results.Failure
import com.janewaitara.gadsleaderboard.model.results.Result

class RemoteApi(private val remoteApiService: RemoteApiService) {

    /**
     * Get Learners Leaders*/

    suspend fun getLearnersLeaders(): Result<List<LearningLeaders>> = try {
        Log.d("Learners", "Most Active learners")

        val learnersLeaders = remoteApiService.getLearningLeaders()

        Log.d("Learners", "${learnersLeaders[0].name} \n ${learnersLeaders[0].hours}")

        Success(learnersLeaders)
    }catch (error: Throwable){
        Failure(error)
    }

    /**
     * Get Skill IQ Leaders*/
    suspend fun getSkillIQLeaders(): Result<List<SkillIQLeaders>> = try {
        Log.d("Skill IQ Learners", "Learners with the highest IQ")

        val skillIQLeaders = remoteApiService.getSkillIQLeaders()

        Log.d("Skill IQ Learners", "${skillIQLeaders[0].name} \n ${skillIQLeaders[0].score}")

        Success(skillIQLeaders)
    }catch (error: Throwable){
        Failure(error)
    }
}