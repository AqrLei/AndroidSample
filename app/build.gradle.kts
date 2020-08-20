plugins {
    id("com.aqrlei.plugin.component")

    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("com.aqrlei.plugin.imageconvert")
}

android {

    // https://android-review.googlesource.com/c/platform/frameworks/support/+/1341853/17/ui/ui-tooling/build.gradle#66
    packagingOptions {
        exclude("META-INF/metadata.jvm.kotlin_module")
        exclude("META-INF/metadata.kotlin_module")
    }

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

    implementation(Deps.androidx.appcompat)
    implementation(Deps.androidx.constraint)
    implementation(Deps.androidx.recyclerview)
    implementation(Deps.androidx.core_ktx)

    implementation(Deps.okhttp.okhttp)
    implementation(Deps.okhttp.logging)

    debugImplementation(Deps.leakCanary)

    testImplementation(Deps.junit)
    testImplementation(Deps.androidx.junit_ext)
    testImplementation(Deps.androidx.junit_ktx_ext)

    implementation(project(":component-helper"))
    implementation(project(":component-widget"))
}

