package com.aqrlei.sample.helper.net

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterIntroEntity( @Json(name = "rich_desc") val richDesc: String? = null)