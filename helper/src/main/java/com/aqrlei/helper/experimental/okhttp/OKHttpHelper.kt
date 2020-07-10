package com.aqrlei.helper.experimental.okhttp

import com.aqrlei.helper.CacheFileHelper
import com.aqrlei.helper.init.ContextInitHelper
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import okio.IOException
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * created by AqrLei on 2020/3/17
 */
class OKHttpHelper {

    private val client = OkHttpClient()


    companion object {
        /**
         * The imgur client ID for OkHttp recipes. If you're using imgur for anything other than running
         * these examples, please request your own client ID! https://api.imgur.com/oauth2
         */
        private val IMGUR_CLIENT_ID = "9199fdef135c122"
        private val MEDIA_TYPE_PNG = "image/png".toMediaType()
        val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
    }

    fun syncGet() {
        val request = Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            println(response.body!!.string())
        }
    }


    fun asyncGet() {
        val request = Request.Builder()
            .url("http://publicobject.com/helloworld.txt")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    println(response.body!!.string())
                }
            }
        })
    }

    fun accessingHeaders() {
        val request = Request.Builder()
            .url("https://api.github.com/repos/square/okhttp/issues")
            .header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/json; q=0.5")
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println("Server: ${response.header("Server")}")
            println("Date: ${response.header("Date")}")
            println("Vary: ${response.headers("Vary")}")
        }
    }


    fun postString() {
        val postBody = """
        |Releases
        |--------
        |
        | * _1.0_ May 6, 2013
        | * _1.1_ June 15, 2013
        | * _1.2_ August 11, 2013
        |""".trimMargin()

        val request = Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(postBody.toRequestBody(MEDIA_TYPE_MARKDOWN))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println(response.body!!.string())
        }
    }


    fun postStreaming() {
        val requestBody = object : RequestBody() {
            override fun contentType() = MEDIA_TYPE_MARKDOWN

            override fun writeTo(sink: BufferedSink) {
                sink.writeUtf8("Numbers\n")
                sink.writeUtf8("-------\n")
                for (i in 2..997) {
                    sink.writeUtf8(String.format(" * $i = ${factor(i)}\n"))
                }
            }

            private fun factor(n: Int): String {
                for (i in 2 until n) {
                    val x = n / i
                    if (x * i == n) return "${factor(x)} × $i"
                }
                return n.toString()
            }
        }

        val request = Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println(response.body!!.string())
        }
    }

    fun postFILE() {
        val file = File("README.md")

        val request = Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(file.asRequestBody(MEDIA_TYPE_MARKDOWN))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println(response.body!!.string())
        }
    }


    fun postFormParameter() {
        val formBody = FormBody.Builder()
            .add("search", "Jurassic Park")
            .build()
        val request = Request.Builder()
            .url("https://en.wikipedia.org/w/index.php")
            .post(formBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println(response.body!!.string())
        }
    }

    fun postMultipart() {
        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("title", "Square Logo")
            .addFormDataPart(
                "image", "logo-square.png",
                File("docs/images/logo-square.png").asRequestBody(MEDIA_TYPE_PNG))
            .build()

        val request = Request.Builder()
            .header("Authorization", "Client-ID $IMGUR_CLIENT_ID")
            .url("https://api.imgur.com/3/image")
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println(response.body!!.string())
        }
    }

    private val moshi = Moshi.Builder().build()
    private val gistJsonAdapter = moshi.adapter(Gist::class.java)

    fun parseJsonResponseWithMoshi() {
        val request = Request.Builder()
            .url("https://api.github.com/gists/c2a7c39532239ff261be")
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val gist = gistJsonAdapter.fromJson(response.body!!.source())

            for ((key, value) in gist!!.files!!) {
                println(key)
                println(value.content)
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Gist(var files: Map<String, GistFile>?)

    @JsonClass(generateAdapter = true)
    data class GistFile(var content: String?)

    private val cacheDirectory = CacheFileHelper.getAppCacheDirFile(ContextInitHelper.getContext(),"cache")
    private val cacheClient: OkHttpClient = OkHttpClient.Builder()
        .cache(
            Cache(
                directory = cacheDirectory,
                maxSize = 10L * 1024L * 1024L // 10 MiB
            ))
        .build()

    fun responseCaching() {
        val request = Request.Builder()
            .url("http://publicobject.com/helloworld.txt")
            .build()

        val response1Body = cacheClient.newCall(request).execute().use {
            if (!it.isSuccessful) throw IOException("Unexpected code $it")

            println("Response 1 response:          $it")
            println("Response 1 cache response:    ${it.cacheResponse}")
            println("Response 1 network response:  ${it.networkResponse}")
            return@use it.body!!.string()
        }

        val response2Body = cacheClient.newCall(request).execute().use {
            if (!it.isSuccessful) throw IOException("Unexpected code $it")

            println("Response 2 response:          $it")
            println("Response 2 cache response:    ${it.cacheResponse}")
            println("Response 2 network response:  ${it.networkResponse}")
            return@use it.body!!.string()
        }

        println("Response 2 equals Response 1? " + (response1Body == response2Body))
    }


    private val executor = Executors.newScheduledThreadPool(1)

    fun cancelCall() {
        val request = Request.Builder()
            .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
            .build()

        val startNanos = System.nanoTime()
        val call = client.newCall(request)

        // Schedule a job to cancel the call in 1 second.
        executor.schedule({
            System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f)
            call.cancel()
            System.out.printf("%.2f Canceled call.%n", (System.nanoTime() - startNanos) / 1e9f)
        }, 1, TimeUnit.SECONDS)

        System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f)
        try {
            call.execute().use { response ->
                System.out.printf(
                    "%.2f Call was expected to fail, but completed: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, response)
            }
        } catch (e: IOException) {
            System.out.printf(
                "%.2f Call failed as expected: %s%n",
                (System.nanoTime() - startNanos) / 1e9f, e)
        }
    }


    private val timeoutClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .build()

    fun timeout() {
        val request = Request.Builder()
            .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
            .build()

        timeoutClient.newCall(request).execute().use { response ->
            println("Response completed: $response")
        }
    }


    fun preCallConfigure() {
        val request = Request.Builder()
            .url("http://httpbin.org/delay/1") // This URL is served with a 1 second delay.
            .build()

        // Copy to customize OkHttp for this request.
        val client1 = client.newBuilder()
            .readTimeout(500, TimeUnit.MILLISECONDS)
            .build()
        try {
            client1.newCall(request).execute().use { response ->
                println("Response 1 succeeded: $response")
            }
        } catch (e: IOException) {
            println("Response 1 failed: $e")
        }

        // Copy to customize OkHttp for this request.
        val client2 = client.newBuilder()
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .build()
        try {
            client2.newCall(request).execute().use { response ->
                println("Response 2 succeeded: $response")
            }
        } catch (e: IOException) {
            println("Response 2 failed: $e")
        }
    }


    private val authenticationClient = OkHttpClient.Builder()
        .authenticator(object : Authenticator {
            @Throws(IOException::class)
            override fun authenticate(route: Route?, response: Response): Request? {
                if (response.request.header("Authorization") != null) {
                    return null // Give up, we've already attempted to authenticate.
                }

                println("Authenticating for response: $response")
                println("Challenges: ${response.challenges()}")
                val credential = Credentials.basic("jesse", "password1")
                return response.request.newBuilder()
                    .header("Authorization", credential)
                    .build()
            }
        })
        .build()

    fun handlingAuthentication() {
        val request = Request.Builder()
            .url("http://publicobject.com/secrets/hellosecret.txt")
            .build()

        authenticationClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println(response.body!!.string())
        }
    }
}