plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(App.compileSdk)

    defaultConfig {
        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)
        versionCode = Helper.versionCode
        versionName = Helper.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(
            "com.aqrlei.helper.log.engine.ILogEngine", "logger",
            "com.aqrlei.helper.log.LogHelper.module(\"${project.getName()}\")"
        )
    }
}

dependencies {

    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(Deps.kotlin.stdlib)
    api(Deps.kotlin.coroutines)

    api(Deps.androidx.appcompat)
    api(Deps.androidx.constraint)
    api(Deps.androidx.recyclerview)
    api(Deps.androidx.core_ktx)

    api(Deps.zxing)
    api(Deps.gson)

    api(Deps.okhttp.okhttp)
    api(Deps.moshi.moshi)
    api(Deps.moshi.moshi_kotlin)
    kapt(Deps.moshi.moshi_kotlin_codegen)

    api(Deps.lib.logHelper)

    testImplementation(Deps.junit)
    testImplementation(Deps.androidx.junit_ext)
    testImplementation(Deps.androidx.junit_ktx_ext)
}
