plugins {
    id("com.aqrlei.plugin.component")
    id("com.aqrlei.plugin.depDeduplicate")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("com.aqrlei.plugin.imageConvert")
}

android {
    compileSdkVersion(App.compileSdk)

    defaultConfig {
        applicationId ="com.aqrlei.sample"
        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        manifestPlaceholders = mapOf("ROUTER_SCHEME" to "androidsample", "ROUTER_HOST" to "aqrlei")
        buildConfigField("String", "ROUTER_SCHEME", "\"androidsample://\"")
        buildConfigField("String", "ROUTER_HOST", "\"aqrlei/\"")
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "aqrlei"
            keyPassword = "aqrlei520"
            storeFile = file("../keystore/aqrlei.keystore")
            storePassword = "aqrlei520"
        }
        create("release") {
            keyAlias = "aqrlei"
            keyPassword = "aqrlei520"
            storeFile = file("../keystore/aqrlei.keystore")
            storePassword = "aqrlei520"
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            //开启混淆
            isMinifyEnabled = true
            // 移除无用资源文件
            isShrinkResources = true
            // zipalign压缩优化
            isZipAlignEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "../proguard/proguard-rules.pro",
                "../proguard/proguard-rules-aliyun.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            ndk {
                abiFilters("armeabi-v7a")
            }
        }
        getByName("debug") {
            //开启混淆
            isMinifyEnabled = false
            // 移除无用资源文件
            isShrinkResources = false
            // zipalign压缩优化
            isZipAlignEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "../proguard/proguard-rules.pro",
                "../proguard/proguard-rules-aliyun.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            ndk {
                abiFilters("armeabi-v7a", "x86")
            }
        }
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar","*.aar"))))

    implementation(Deps.kotlin.stdlib)
    implementation(Deps.kotlin.coroutines)

    implementation(Deps.androidx.appcompat)
    implementation(Deps.androidx.constraint)
    implementation(Deps.androidx.recyclerview)
    implementation(Deps.androidx.core_ktx)

    implementation(Deps.zxing)
    implementation(Deps.gson)

    implementation(Deps.exoplayer.core)
    implementation(Deps.exoplayer.dash)
    implementation(Deps.exoplayer.hls)
    implementation(Deps.exoplayer.smoothStreaming)
    implementation(Deps.exoplayer.ui)

    api(Deps.okhttp.okhttp)
    api(Deps.okhttp.logging)
    api(Deps.moshi.moshi)
    api(Deps.moshi.moshi_kotlin)
    kapt(Deps.moshi.moshi_kotlin_codegen)
    debugImplementation(Deps.leakCanary)

    testImplementation(Deps.junit)
    testImplementation(Deps.androidx.junit_ext)
    testImplementation(Deps.androidx.junit_ktx_ext)

    implementation(Deps.lib.bannerView)
    implementation(Deps.lib.widget_collection)

    implementation(project(":component-helper"))
    implementation(project(":component-widget"))
}


