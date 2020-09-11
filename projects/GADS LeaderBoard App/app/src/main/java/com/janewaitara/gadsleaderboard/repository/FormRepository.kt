package com.janewaitara.gadsleaderboard.repository

import com.janewaitara.gadsleaderboard.networking.PostRemoteApi

class FormRepository(private val postRemoteApi: PostRemoteApi) {

    suspend fun submitFormData(firstName: String, lastName: String, emailAddress: String, gitHubLink: String )
            = postRemoteApi.postFormData(firstName, lastName, emailAddress, gitHubLink )

}