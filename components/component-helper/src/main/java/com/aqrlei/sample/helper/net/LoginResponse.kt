package com.aqrlei.sample.helper.net

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    var errorCode: Int,
    var errorMsg: String?,
    var data: Data
)

@JsonClass(generateAdapter = true)
data class Data(
    var id: Int,
    var username: String,
    var password: String,
    var icon: String?,
    var type: Int,
    var collectIds: List<Int>?
)