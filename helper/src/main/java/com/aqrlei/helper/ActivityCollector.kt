package com.aqrlei.helper

import android.app.Activity

/**
 * Created by AqrLei on 2019-05-28
 */

object ActivityCollector {
    private val activities = ArrayList<Activity>()
    fun add(activity: Activity) {
        activities.add(activity)
    }

    fun remove(activity: Activity) {
        activities.remove(activity)
    }

    fun killApp() {
        activities.filter { !it.isFinishing }
            .forEach { it.finish() }
        activities.clear()
    }
}