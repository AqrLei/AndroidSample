package com.aqrlei.sample.base.page

/**
 * created by AqrLei on 2020/6/2
 */
var referPageName: String? = null

interface IPage {
    fun getPageName(): String
    fun getReferPageName(): String
}