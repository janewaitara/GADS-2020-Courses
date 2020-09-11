package com.janewaitara.gadsleaderboard.model.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SkillIQLeaders (var name: String,
                           var score: Int,
                           var country: String,
                           var badgeUrl: String)