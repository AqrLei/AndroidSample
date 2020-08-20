package com.aqrlei.sample.base

import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.aqrlei.utilcollection.ContextInitUtil
import com.aqrlei.utilcollection.toast.BaseToast
import kotlinx.android.synthetic.main.toast.view.*

/**
 * created by AqrLei on 2020/3/17
 */
object ToastWrapper : BaseToast() {

    override fun initToast(type: Int?): Toast {
        return NormalToast.instance
    }

    override fun getTextViewID(type: Int?): Int = R.id.tvToast

    internal object NormalToast {
        val instance = ContextInitUtil.getContext()?.let { context ->
            val view = View.inflate(context, R.layout.toast, null)
            val w = context.resources.displayMetrics.widthPixels
            val h = context.resources.displayMetrics.heightPixels
            view.tvToast.minWidth = w / 4
            view.tvToast.maxWidth = (w * 3) / 4

            Toast(context).also {
                it.view = view
                it.setGravity(Gravity.BOTTOM, 0, h/8)
            }
        }?:throw NullPointerException("Must invoking ContextInitUtil.doInit() first")
    }

    internal object WarningToast {

    }

    object ToastType {
        const val WARNING = 100
        const val NORMAL = 200
        const val ERROR = 300
    }
}