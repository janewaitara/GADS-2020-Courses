package com.janewaitara.gadsleaderboard.networking

import com.janewaitara.gadsleaderboard.model.results.*

class PostRemoteApi (private val postRemoteApiService: PostRemoteApiService){

    /**
     * Post to the form*/
    suspend fun postFormData(firstName: String, lastName: String, emailAddress: String, gitHubLink: String): Result<Void> = try{

        Success(postRemoteApiService.submitFormData(firstName, lastName, emailAddress, gitHubLink))

    }catch(error: Throwable){

        Failure(error)
    }
}