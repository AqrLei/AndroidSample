package com.aqrlei.helper.toast

/**
 * created by AqrLei on 2020/3/17
 */
interface IToast {

    fun shortShow(message: String, type: Int? = null)
    fun longShow(message: String, type: Int? = null)

}