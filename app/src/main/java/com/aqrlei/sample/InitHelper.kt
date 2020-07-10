package com.aqrlei.sample

import android.content.Context
import com.aqrlei.helper.net.OkHttpHelper
import com.aqrlei.helper.imagehandler.ImageHandleHelper
import com.aqrlei.helper.net.OkHttpRequest
import com.aqrlei.helper.net.controller.factory.DefaultHttpRequestFactory
import com.aqrlei.helper.net.transformer.json.factory.DefaultJsonTransformerFactory
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