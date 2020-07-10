package com.aqrlei.helper.experimental.coroutine

import org.junit.Test

/**
 * created by AqrLei on 2020/3/18
 */

class CoroutineTest {

    @Test
   fun testInterceptor(){
        CoroutinesTest.runInterceptor()
    }

    @Test
    fun testExceptionHandler(){
        CoroutinesTest.runExceptionHandler()
    }
}