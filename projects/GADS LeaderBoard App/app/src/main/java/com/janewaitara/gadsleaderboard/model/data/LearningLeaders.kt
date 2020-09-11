package com.janewaitara.gadsleaderboard.model.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LearningLeaders(var name: String,
                           var hours: Int,
                           var country: String,
                           var badgeUrl: String)