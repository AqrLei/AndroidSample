package com.aqrlei.helper.experimental.coroutine

import android.os.Parcelable
import com.aqrlei.helper.experimental.coroutine.CoroutinesTest.TAG
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.*

/**
 * created by AqrLei on 2020/3/18
 */
suspend inline fun Job.Key.currentJob() = coroutineContext[Job]

/**
 * 协程拦截器
 */
class SimpleContinuationInterceptor : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*>
        get() = ContinuationInterceptor


    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return SimpleContinuation(continuation)
    }
}

class SimpleContinuation<T>(private val continuation: Continuation<T>) : Continuation<T> {
    override val context: CoroutineContext
        get() = continuation.context

    override fun resumeWith(result: Result<T>) {
        println("$TAG - Continuation: $result - before")
        continuation.resumeWith(result)
        println("$TAG - Continuation: $result - after")

    }
}

/**
 * 异常处理
 */
@Parcelize
class User(val name: String?, val authToken: String?) : Parcelable {
    companion object {
        fun from(string: String): User {
            return User("", "")
        }
    }
}

//某个协程中的异常处理 通过 async启动的协程出现未捕获的异常时会被忽略
val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    println("exception with message: ${throwable.message}")
}

interface Callback<T> {
    fun onSuccess(value: T)
    fun onError(t: Throwable)

}


interface CoroutineHe

fun getUserUser() {

    GlobalScope.launch {
        val call = OkHttpClient().newCall(
            Request.Builder().get().url("http://api.github.com/users/AqrLei")
                .build()
        )
    }
}

