package com.aqrlei.widgets.ext

import android.view.View
import com.aqrlei.helper.ToastHelper

/**
 * created by AqrLei on 2020/5/23
 */

private var lastClickTime: Long = 0L

fun View.setOnAvoidFastClickListener(
    clickInterVal: Long = 1000L,
    fastClickTip: String? = null,
    avoidFastClick: (v: View) -> Unit) {

    this.setOnClickListener {
        if (System.currentTimeMillis() - lastClickTime > clickInterVal) {
            lastClickTime = System.currentTimeMillis()
            avoidFastClick(this)
        } else if (!fastClickTip.isNullOrEmpty()) {
            ToastHelper.shortShow(fastClickTip)
        }
    }
}