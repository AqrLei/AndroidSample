package com.aqrlei.helper.cache.lru.encrypt

import com.aqrlei.helper.StringHelper
import com.aqrlei.helper.cache.lru.ICacheKeyEncrypt

/**
 * created by AqrLei on 2020/4/22
 */
class MD5CacheKeyEncrypt :ICacheKeyEncrypt{
    override fun encrypt(key: String): String {
        return StringHelper.md5Encode(key)
    }
}