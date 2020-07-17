package com.aqrlei.helper.toast

import com.aqrlei.helper.init.ContextInitHelper

/**
 * created by AqrLei on 2020/3/17
 */
object ToastHelper {

    @JvmStatic
    fun shortShow(message: String) {
        ToastWrapper.shortShow(message)
    }

    @JvmStatic
    fun shortShow(message: String, type: Int) {
        ToastWrapper.shortShow(message, type)
    }

    @JvmStatic
    fun shortShow(resId: Int) {
        shortShow(
            ContextInitHelper.getString(
                resId
            )
        )
    }

    @JvmStatic
    fun shortShow(resId: Int, type: Int) {
        shortShow(
            ContextInitHelper.getString(
                resId
            ), type
        )
    }


    @JvmStatic
    fun longShow(message: String) {
        ToastWrapper.longShow(message)
    }

    @JvmStatic
    fun longShow(message: String, type: Int) {
        ToastWrapper.longShow(message, type)
    }

    @JvmStatic
    fun longShow(resId: Int) {
        longShow(
            ContextInitHelper.getString(
                resId
            )
        )
    }

    @JvmStatic
    fun longShow(resId: Int, type: Int) {
        longShow(
            ContextInitHelper.getString(
                resId
            ), type
        )
    }
}