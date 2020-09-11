package com.janewaitara.gadsleaderboard.networking

import retrofit2.Response

class PostRemoteApi (private val postRemoteApiService: PostRemoteApiService){

    /**
     * Post to the form*/
    suspend fun postFormData(firstName: String, lastName: String, emailAddress: String, gitHubLink: String): Response<Void> {

     return postRemoteApiService.submitFormData(firstName, lastName, emailAddress, gitHubLink)

    }
}