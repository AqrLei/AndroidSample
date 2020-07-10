package com.aqrlei.helper.cache.lru

/**
 * created by AqrLei on 2020-01-02
 */
interface ICacheKeyEncrypt {
    fun encrypt(key: String): String
}