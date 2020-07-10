package com.aqrlei.helper.experimental.coroutine

import kotlinx.coroutines.*

/**
 * created by AqrLei on 2020/3/18
 */
/**
 * 1.拦截器有疑问 ，什么时候能够取到值
 *
 */
object CoroutinesTest {

    val TAG = "[${Thread.currentThread()}]"

    fun runInterceptor() = runBlocking {
        GlobalScope.launch(SimpleContinuationInterceptor() + CoroutineName("hello")) {
            val deffer = async {
                delay(1000) // 必须有这个，才能拦截到 ？
                "Hello kotlin"
            }
            println("$TAG -  ${deffer.await()}")
        }.join()
        println("$TAG -  out")
    }



    fun runExceptionHandler() = runBlocking {
        GlobalScope.launch(exceptionHandler){
            throw ArithmeticException("Hello")
        }.join()
    }
}