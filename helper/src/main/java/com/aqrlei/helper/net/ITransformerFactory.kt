package com.aqrlei.helper.net

/**
 * created by AqrLei on 2020/3/20
 */
interface ITransformerFactory {
    fun <T> createTransformer(): ITransformer<T>
}