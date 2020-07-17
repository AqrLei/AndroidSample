plugins {
    id("com.aqrlei.plugin.component")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(App.compileSdk)

    defaultConfig {
        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }


    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
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
    api(Deps.lib.guide)

    api(project(":helper"))
    api(project(":base"))
}


