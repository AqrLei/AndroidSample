package com.aqrlei.sample.helper

import android.net.Uri
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aqrlei.sample.helper.net.NetFragment
import com.aqrlei.utilcollection.toast.ToastHelper

/**
 * created by AqrLei on 2020/5/22
 */

const val SCHEME_HOST = "component://helper/"

const val KEY_PAGE_DATA = "page_data"

object Helper {
    const val FILE = "file"
    const val IGNORE_BATTERY = "ignore_battery"
    const val IMAGE = "image"
    const val PERMISSION = "permission"
    const val NET = "net"
    const val TEST = "test"
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

    FILE(Helper.FILE) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return FileFragment::class.java.newInstance()
        }
    },
    IGNORE_BATTERY(Helper.IGNORE_BATTERY) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return IgnoreBatteryFragment::class.java.newInstance()
        }
    },
    IMAGE(Helper.IMAGE) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return ImageFragment::class.java.newInstance()
        }
    },
    PERMISSION(Helper.PERMISSION) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return PermissionFragment::class.java.newInstance()
        }
    },
    NET(Helper.NET) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return NetFragment::class.java.newInstance()
        }
    },
    TEST(Helper.TEST) {
        override fun routerTarget(paramMap: Map<String, String>?): Fragment {
            return TestFragment::class.java.newInstance()
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