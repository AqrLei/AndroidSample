package com.aqrlei.helper.net.transformer

import com.aqrlei.helper.BuildConfig.logger
import com.aqrlei.helper.net.IS_DEBUG
import com.aqrlei.helper.net.ITransformer

/**
 * created by AqrLei on 2020/4/21
 */
abstract class AbstractTransformer<T> : ITransformer<T> {
    protected val tag = "Transformer"

    protected fun log(message:String){
        if (IS_DEBUG) {
            logger.d(tag, message)
        }
    }
}