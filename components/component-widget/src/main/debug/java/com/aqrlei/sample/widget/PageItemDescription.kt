package com.aqrlei.sample.widget

import android.content.Context
import android.os.Parcelable
import com.aqrlei.sample.base.page.IPageRouter

import kotlinx.android.parcel.Parcelize

/**
 * created by AqrLei on 2019-12-28
 */
@Parcelize
data class PageItemDescription(
    val routerPath: String,
    val mKitName: String,
    val level: Int = 1
) : Parcelable,IPageRouter {
   override fun router(context: Context?) {
        SchemeActivity.schemeRouter(context, SCHEME_HOST + routerPath,this)
    }

    override fun toString(): String {
        return "[routerPath : $routerPath, mKitName : $mKitName , level : $level]"
    }
}