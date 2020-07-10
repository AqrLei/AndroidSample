package com.aqrlei.helper.init

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat

/**
 * created by AqrLei on 2020/3/17
 */
object ContextInitHelper {
    private lateinit var context: Context
    private lateinit var application: Application
    val resources: Resources
        get() = context.resources

    @JvmStatic
    fun doInit(application: Application) {
        ContextInitHelper.application = application
        context = application.applicationContext
    }

    @JvmStatic
    fun getContext(): Context {
        return context
    }

    @JvmStatic
    fun getApplication(): Application {
        return application
    }

    @JvmStatic
    fun getString(resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }

    @JvmStatic
    fun getColor(resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    @JvmStatic
    fun getDimenPixel(resId: Int): Int {
        return resources.getDimensionPixelSize(resId)
    }
}