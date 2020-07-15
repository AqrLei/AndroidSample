package com.aqrlei.widgets.guide

import android.app.Activity
import androidx.fragment.app.Fragment
import com.aqrlei.widgets.guide.core.Builder

/**
 * Created by AqrLei on 2019-08-20
 */
class GuideManager {

    companion object {
        @JvmStatic
        fun with(activity: Activity, block: Builder.() -> Unit): Builder {
            return Builder(activity).apply(block)
        }

        @JvmStatic
        fun with(fragment: Fragment, block: Builder.() -> Unit): Builder {
            return Builder(fragment).apply(block)
        }
    }
}