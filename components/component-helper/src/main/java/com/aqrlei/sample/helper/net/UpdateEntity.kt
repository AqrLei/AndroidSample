package com.aqrlei.sample.helper.net

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * created by AqrLei on 2020/3/23
 */
@JsonClass(generateAdapter = true)
data class UpdateEntity(val android: Android)

@JsonClass(generateAdapter = true)
data class Android(
    val desc: String?,
    @Json(name = "version_name") val versionName: String?,
    @Json(name = "version_code") val versionCode: String?,
    @Json(name = "below_force_update_version_code") val belowForceUpdateVersionCode: String?,
    @Json(name = "below_force_login_version_code") val belowForceLoginVersionCode: String?,
    val md5: String?,
    @Json(name = "app_url") val appUrl: String
){
    fun string():String{
        return "[ versionName : $versionName,\n" +
                " versionCode $versionCode,\n" +
                " belowForceUpdateVersionCode : $belowForceUpdateVersionCode\n" +
                " belowForceLoginVersionCode : $belowForceLoginVersionCode\n" +
                " md5 : $md5\n" +
                " appUrl : $appUrl ]"
    }
}