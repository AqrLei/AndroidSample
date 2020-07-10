package com.aqrlei.helper.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.aqrlei.helper.R

/**
 * created by AqrLei on 2020/4/22
 */
abstract class BaseSimpleDialogFragment : DialogFragment() {


    var touchOutsideCancelable: Boolean = false

    var clickCancelable: Boolean = false


    @LayoutRes
    abstract fun getContentLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.DefaultDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContentLayout(), container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            isCancelable = clickCancelable
            dialog!!.setCanceledOnTouchOutside(touchOutsideCancelable)
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            it.decorView.setPadding(0, 0, 0, 0)
        }
    }
}