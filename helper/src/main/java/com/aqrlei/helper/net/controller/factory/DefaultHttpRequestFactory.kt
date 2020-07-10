package com.aqrlei.helper.net.controller.factory

import com.aqrlei.helper.net.IHttpCallback
import com.aqrlei.helper.net.IHttpRequestController
import com.aqrlei.helper.net.IHttpRequestFactory
import com.aqrlei.helper.net.ITransformer
import com.aqrlei.helper.net.controller.CoroutineRequestController

/**
 * created by AqrLei on 2020/3/20
 */
class DefaultHttpRequestFactory private constructor() :
    IHttpRequestFactory {
    companion object {
        fun create() = DefaultHttpRequestFactory()
    }

    override fun <T> createHttpRequest(
        callback: IHttpCallback<T>,
        transformer: ITransformer<T>): IHttpRequestController<T> {
        return CoroutineRequestController.build(callback, transformer)
    }

}