package com.aqrlei.helper

import java.text.SimpleDateFormat
import java.util.*

/**
 * created by AqrLei on 2020-02-13
 */

object CalendarHelper {
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    private val yearFormat = SimpleDateFormat("yyyy", Locale.CHINA)
    private val monthFormat = SimpleDateFormat("MM", Locale.CHINA)
    private val dayFormat = SimpleDateFormat("dd", Locale.CHINA)

    fun getThisYear(): String {
        val cale = Calendar.getInstance()
        cale.add(Calendar.YEAR, 0)
        return yearFormat.format(cale.time)
    }

    fun getLastYear(): String {
        val cale = Calendar.getInstance()
        cale.add(Calendar.YEAR, -1)
        return yearFormat.format(cale.time)
    }

    fun getThisMonth(): String {
        val cale = Calendar.getInstance()
        cale.add(Calendar.MONTH, 0)
        return monthFormat.format(cale.time)
    }

    fun getLastMonth(): String {
        val cale = Calendar.getInstance()
        cale.add(Calendar.MONTH, -1)
        return monthFormat.format(cale.time)
    }

    fun getLastMonthDate(): String {
        val cale = Calendar.getInstance()
        cale.add(Calendar.MONTH, -3)
        return format.format(cale.time)
    }


    fun getBeginMonthOfYear(): String {
        val cale = Calendar.getInstance()
        cale.add(Calendar.YEAR, 0)
        cale[Calendar.MONTH] = 0
        return monthFormat.format(cale.time)
    }

    fun getThisMonthStart(): String {
        val cale = Calendar.getInstance()
        cale.add(Calendar.MONTH, 0)
        cale[Calendar.DAY_OF_MONTH] = 1
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = 0
        return format.format(cale.time)
    }

    fun getDayOfMonth(): Int {
        val cale = Calendar.getInstance()
        return cale.get(Calendar.DAY_OF_MONTH)
    }

    fun getTodayStart(): String {
        val cale = Calendar.getInstance()
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = 0
        return format.format(cale.time)
    }

    fun getTodayEnd(): String {
        val cale = Calendar.getInstance()
        return format.format(cale.time)
    }

    fun getLastMonthStart(): String {
        val cale = Calendar.getInstance()
        cale.add(Calendar.MONTH, -1)
        cale[Calendar.DAY_OF_MONTH] = 1
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = 0
        return format.format(cale.time)
    }

    fun getLastMonthEnd(): String {
        val cale = Calendar.getInstance()
        cale[Calendar.DAY_OF_MONTH] = 1
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = -1
        return format.format(cale.time)
    }

    fun getYesterdayStart(): String {
        val cale = Calendar.getInstance()
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = 0
        cale[Calendar.HOUR_OF_DAY] = -24
        return format.format(cale.time)
    }

    fun getYesterdayEnd(): String {
        val cale = Calendar.getInstance()
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = -1
        return format.format(cale.time)
    }

    fun getSevenDaysStart(): String {
        val cale = Calendar.getInstance()
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = 0
        cale[Calendar.HOUR_OF_DAY] = -24 * 7
        return format.format(cale.time)
    }

    fun selectStartDate(date: Date?): String {
        val cale = Calendar.getInstance()
        cale.time = date
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = 0
        return format.format(cale.time)
    }

    fun selectEndDate(date: Date?): String {
        val cale = Calendar.getInstance()
        cale.time = date
        cale[Calendar.HOUR_OF_DAY] = 24
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = -1
        return format.format(cale.time)
    }

    fun selectMonthStartDate(date: Date?): String {
        val cale = Calendar.getInstance()
        cale.time = date
        cale[Calendar.DAY_OF_MONTH] = 1
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = 0
        return format.format(cale.time)
    }

    fun selectMonthEndDate(date: Date?): String {
        val cale = Calendar.getInstance()
        cale.time = date
        cale[Calendar.MONTH] = cale[Calendar.MONTH] + 1
        cale[Calendar.DAY_OF_MONTH] = 1
        cale[Calendar.HOUR_OF_DAY] = 0
        cale[Calendar.MINUTE] = 0
        cale[Calendar.SECOND] = -1
        return format.format(cale.time)
    }
}