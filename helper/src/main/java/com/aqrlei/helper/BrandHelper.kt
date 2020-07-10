package com.aqrlei.helper

import android.content.Context
import android.os.Build

/**
 * created by AqrLei on 2019-12-26
 */
object BrandHelper {

    fun isHuawei() = if (Build.BRAND.isNullOrEmpty()) false
    else Build.BRAND.toLowerCase() == "huawei" || Build.BRAND.toLowerCase() == "honor"

    fun goHuaweiSetting(context: Context?){
        try {
            AppHelper.showActivity(
                context,
                "com.huawei.systemmanager",
                "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity")
        } catch (e: Exception) {
            AppHelper.showActivity(
                context,
                "com.huawei.systemmanager",
                "com.huawei.systemmanager.optimize.bootstart.BootStartActivity")
        }
    }

    fun isXiaomi() = if (Build.BRAND.isNullOrEmpty()) false else  Build.BRAND.toLowerCase() == "xiaomi"

    fun toXiaomiSetting(context: Context?){
        AppHelper.showActivity(context,"com.miui.securitycenter",
            "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }

    fun isOppo() = if (Build.BRAND.isNullOrEmpty()) false else Build.BRAND.toLowerCase() == "oppo"

    fun toOppoSetting(context: Context?){
        try {
            AppHelper.showActivity(context,"com.coloros.phonemanager")
        } catch (e1: java.lang.Exception) {
            try {
                AppHelper.showActivity(context,"com.oppo.safe")
            } catch (e2: java.lang.Exception) {
                try {
                    AppHelper.showActivity(context,"com.coloros.oppoguardelf")
                } catch (e3: java.lang.Exception) {
                    AppHelper.showActivity(context,"com.coloros.safecenter")
                }
            }
        }
    }

    fun isVivo() = if (Build.BRAND.isNullOrEmpty()) false else Build.BRAND.toLowerCase() == "vivo"

    fun toVivoSetting(context: Context?){
        AppHelper.showActivity(context,"com.iqoo.secure")
    }

    fun isMeizu() = if (Build.BRAND.isNullOrEmpty()) false else Build.BRAND.toLowerCase() == "meizu"

    fun toMeizuSetting(context: Context?){
        AppHelper.showActivity(context,"com.meizu.safe")
    }

    fun isSamsung() = if (Build.BRAND.isNullOrEmpty()) false else Build.BRAND.toLowerCase() == "samsung"

    fun toSamsungSetting(context: Context?){
        try {
            AppHelper.showActivity(context,"com.samsung.android.sm_cn")
        }catch (e:Exception){
            AppHelper.showActivity(context,"com.samsung.android.sm")
        }
    }
}