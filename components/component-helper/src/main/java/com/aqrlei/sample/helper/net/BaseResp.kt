package com.aqrlei.sample.helper.net

import com.squareup.moshi.JsonClass

/**
 * created by AqrLei on 2020/3/19
 */
@JsonClass(generateAdapter = true)
data class BaseResp<T>(val code:String,val msg:String,val data:T?)