import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

/**
 * created by AqrLei on 2020/6/8
 */
fun addRepos(handler: RepositoryHandler) {
    handler.maven { url = URI.create("https://dl.bintray.com/aqrlei/lib") }
    handler.google()
    handler.jcenter()
    handler.mavenCentral()


}

object App {
    const val versionCode = 1000001
    const val versionName = "1.0.0.1"

    const val minSdk = 21
    const val compileSdk = 29
    const val targetSdk = 29
}

object ClassPath {
    const val android_gradle = "com.android.tools.build:gradle:4.0.0"
    const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val image_convert = "com.aqrlei.plugin:imageconvert:1.0.0"
    const val component = "com.aqrlei.plugin:component:1.0.1"
}

class Lib {
    val bannerView = "com.aqrlei.lib:bannerview:1.0.1"
    val logHelper = "com.aqrlei.lib:litelog:1.0.2"
    val widget_collection = "com.aqrlei.lib:widgetcollection:1.0.1"
    val util_collection = "com.aqrlei.lib:utilcollection:1.0.1"
    val guide = "com.aqrlei.lib:guide:1.0.1"
    val permission = "com.aqrlei.lib:litepermission:1.0.1"
    val cache = "com.aqrlei.lib:litecache:1.0.1"
    val imageHandler = "com.aqrlei.lib:litedraw:1.0.1"

    val net = "com.aqrlei.lib:litenet:1.0.1"

}

object Deps {
    val androidx = Androidx()
    val exoplayer = ExoPlayer()
    val gson = "com.google.code.gson:gson:2.8.6"
    val junit = "junit:junit:4.12"
    val kotlin = Kotlin()
    val lib = Lib()
    val liteNet = LiteNet()
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"
    val material = "com.google.android.material:material:1.2.0"

    val okhttp = SquareUp.OkHttp()
    val moshi = SquareUp.Moshi()

}

class Androidx {
    val appcompat = "androidx.appcompat:appcompat:1.1.0"
    val constraint = "androidx.constraintlayout:constraintlayout:1.1.3"
    val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
    val core_ktx = "androidx.core:core-ktx:1.3.0"

    val runner = "androidx.test:runner:1.2.0"
    val junit_ext = "androidx.test.ext:junit:1.1.1"
    val junit_ktx_ext = "androidx.test.ext:junit-ktx:1.1.1"
}

class ExoPlayer {
    val core = "com.google.android.exoplayer:exoplayer-core:${Versions.exoPlayer}"
    val dash = "com.google.android.exoplayer:exoplayer-dash:${Versions.exoPlayer}"
    val hls = "com.google.android.exoplayer:exoplayer-hls:${Versions.exoPlayer}"
    val smoothStreaming =
        "com.google.android.exoplayer:exoplayer-smoothstreaming:${Versions.exoPlayer}"
    val ui = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoPlayer}"
}

class Kotlin {
    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
}

sealed class SquareUp {
    class OkHttp : SquareUp() {
        val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    }

    class Moshi : SquareUp() {
        val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        val moshi_kotlin_codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }
}

class LiteNet() {
    val litenet = "com.aqrlei.lib:litenet:1.0.2"
    val transformer_moshi = "com.aqrlei.lib:litenet-transformer-moshi:1.0.2"
    val transformer_common = "com.aqrlei.lib:litenet-transformer-common:1.0.2"
    val request_coroutine = "com.aqrlei.lib:litenet-request-coroutine:1.0.2"
}

private object Versions {
    const val exoPlayer = "2.11.5"

    const val kotlin = "1.4.0"
    const val kotlin_coroutines = "1.3.9"

    const val okhttp = "4.4.1"
    const val moshi = "1.9.2"
}