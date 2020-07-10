package com.aqrlei.helper.cache.lru

/**
 * Created by AqrLei on 2019-07-01
 */
abstract class AbstractCache(protected val cacheEncrypt: ICacheEncrypt) : IAppCache {

    fun encrypt(key: String, byteArray: ByteArray?): ByteArray? {
        return cacheEncrypt.encrypt(key, byteArray)
    }

    fun decrypt(key: String, byteArray: ByteArray?): ByteArray? {
        return cacheEncrypt.decrypt(key, byteArray)
    }
}