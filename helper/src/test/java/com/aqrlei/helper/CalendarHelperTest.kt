package com.aqrlei.helper

import android.util.Log
import org.junit.Test

/**
 * created by AqrLei on 2020-02-13
 */
class CalendarHelperTest {

    @Test
    fun getThisYear() {
        println("This Year: " + CalendarHelper.getThisYear())
    }

    @Test
    fun getLastYear() {
        println("Last Year: " + CalendarHelper.getLastYear())
    }

    @Test
    fun getThisMonth() {
        println("This Month: " + CalendarHelper.getThisMonth())
    }

    @Test
    fun getLastMonth() {
        println("Last month: " + CalendarHelper.getLastMonth())
    }

    @Test
    fun getBeginMonthOfYear() {
        println("The begin month of year: " + CalendarHelper.getBeginMonthOfYear())
    }

    @Test
    fun getThisMonthStart() {
        println("This month start: " + CalendarHelper.getThisMonthStart())
    }

    @Test
    fun getTodayStart() {
        println("The today start: " + CalendarHelper.getTodayStart())
    }

    @Test
    fun getTodayEnd() {
        println("The today end: " + CalendarHelper.getTodayEnd())
    }

    @Test
    fun getLastMonthStart() {
        println("The last month start: " + CalendarHelper.getLastMonthStart())
    }

    @Test
    fun getLastMonthEnd() {
        println("The last month end: " + CalendarHelper.getLastMonthEnd())
    }

    @Test
    fun getYesterdayStart() {
        println("The yesterday start: " + CalendarHelper.getYesterdayStart())
    }

    @Test
    fun getYesterdayEnd() {
        println("The yesterday end: " + CalendarHelper.getYesterdayEnd())
    }

    @Test
    fun getSevenDaysStart() {
        println("The seven days start: " + CalendarHelper.getSevenDaysStart())
    }

    @Test
    fun getDayOfMonth(){
        println("The date of Today: " + CalendarHelper.getDayOfMonth())
    }

    fun getLastMonthDate(){
        println("The date of Today: " + CalendarHelper.getLastMonthDate())
    }
}