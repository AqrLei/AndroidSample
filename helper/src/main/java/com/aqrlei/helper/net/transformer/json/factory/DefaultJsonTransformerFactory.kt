package com.aqrlei.helper.net.transformer.json.factory

import com.aqrlei.helper.net.ITransformer
import com.aqrlei.helper.net.ITransformerFactory
import com.aqrlei.helper.net.transformer.json.MoshiJsonTransformer

/**
 * created by AqrLei on 2020/3/20
 */
class DefaultJsonTransformerFactory private constructor() :
    ITransformerFactory {
    companion object {
        fun create() =
            DefaultJsonTransformerFactory()
    }

    override fun <T> createTransformer(): ITransformer<T> {
        return MoshiJsonTransformer.createJsonParser()
    }
}