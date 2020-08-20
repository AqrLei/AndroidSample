package com.aqrlei.sample

import android.content.Context
import com.aqrlei.litenet.OkHttpHelper
import com.aqrlei.litedraw.ImageHandleHelper
import com.aqrlei.litenet.OkHttpRequest
import com.aqrlei.litenet.controller.factory.DefaultHttpRequestFactory
import com.aqrlei.litenet.transformer.json.factory.DefaultJsonTransformerFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * created by AqrLei on 2020/4/11
 */
object InitHelper {
    fun init(context: Context) {
        ImageHandleHelper.init(context)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        OkHttpHelper.init(
            OkHttpRequest.Builder()
                .okHttpClient(okHttpClient)
                .setTransformerFactory(DefaultJsonTransformerFactory.create())
                .setHttpRequestFactory(DefaultHttpRequestFactory.create())
                .isDebug(true)
                .build())
    }
}