package com.aqrlei.sample.widget

import android.net.Uri
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aqrlei.sample.widget.banner.Banner2Fragment
import com.aqrlei.sample.widget.banner.BannerFragment
import com.aqrlei.sample.widget.banner.BannerTestFragment
import com.aqrlei.util.toast.ToastHelper

/**
 * created by AqrLei on 2020/5/22
 */

const val SCHEME_HOST = "component://widget/"

const val KEY_PAGE_DATA = "page_data"

const val KEY_TYPE = "type"

object BannerType {
    const val TYPE_2 = "0"
    const val TYPE_TEST = "1"
}

object Widget {
    const val BANNER = "banner"
    const val BANNER2 = "${BANNER}?${KEY_TYPE}=${BannerType.TYPE_2}"
    const val BANNER_TEST = "${BANNER}?${KEY_TYPE}=${BannerType.TYPE_TEST}"
    const val AUTO_DRAG = "auto_drag"
    const val FLOW_RECYCLER = "flow_recycler"
    const val GUIDE = "guide"
    const val SHADOW = "shadow"
    const val SMOOTH_SLIDE = "smooth_slide"
    const val TEXT_SPAN = "text_span"

}

object SchemeHelper {

    fun schemeRouter(activity: AppCompatActivity, @IdRes containerId: Int, uri: Uri?) {
        uri ?: return
        try {
            val schemePath = lastPathSegment(uri) ?: ""
            val paramMap = queryParameterMap(uri)
            SchemeRouterEnum.valueOf(schemePath).router(activity, containerId, paramMap)
        } catch (e: IllegalArgumentException) {
            ToastHelper.shortShow("暂不支持")
        }
    }

    private fun lastPathSegment(uri: Uri) = uri.lastPathSegment?.toUpperCase()

    private fun queryParameterMap(uri: Uri): Map<String, String> {
        val queryParameterNames = uri.queryParameterNames
        val queryParameterMap = mutableMapOf<String, String>()
        for (queryParameterName in queryParameterNames) {
            val value = uri.getQueryParameter(queryParameterName) ?: ""
            queryParameterMap[queryParameterName] = value
        }
        return queryParameterMap
    }

}

enum class SchemeRouterEnum(private val tagPath: String) {

    BANNER(Widget.BANNER) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return when (paramMap?.get(KEY_TYPE)) {
                BannerType.TYPE_2 -> Banner2Fragment::class.java.newInstance()
                BannerType.TYPE_TEST -> BannerTestFragment::class.java.newInstance()
                else -> BannerFragment::class.java.newInstance()
            }
        }
    },
    AUTO_DRAG(Widget.AUTO_DRAG) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return AutoDragFragment::class.java.newInstance()
        }
    },
    FLOW_RECYCLER(Widget.FLOW_RECYCLER) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return FlowRecyclerFragment::class.java.newInstance()
        }
    },
    GUIDE(Widget.GUIDE) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return GuideFragment::class.java.newInstance()
        }
    },
    SHADOW(Widget.SHADOW) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return ShadowFragment::class.java.newInstance()
        }
    },
    SMOOTH_SLIDE(Widget.SMOOTH_SLIDE) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return SmoothSlideFragment::class.java.newInstance()
        }
    },
    TEXT_SPAN(Widget.TEXT_SPAN) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return TextSpanFragment::class.java.newInstance()
        }
    };

    fun router(
        activity: AppCompatActivity,
        @IdRes containerId: Int,
        paramMap: Map<String, String>? = null
    ) {
        val fm = activity.supportFragmentManager
        fm.beginTransaction().also { transaction ->

            val frag = fm.findFragmentByTag(tagPath) ?: routerTarget(paramMap)
            val replace = {
                transaction.replace(containerId, frag, tagPath)
                transaction.commit()
            }
            replace.invoke()
        }
    }

    protected abstract fun routerTarget(paramMap: Map<String, String>?): Fragment

}