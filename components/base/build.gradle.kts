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
        getByName("release") {
            isMinifyEnabled = false
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

    api(Deps.material)



    api(Deps.lib.cache)
    api(Deps.lib.net)
    api(Deps.lib.permission)
    api(Deps.lib.imageHandler)
    api(Deps.lib.logHelper)
    api(Deps.lib.util_collection)
    api(Deps.lib.guide)
    api(Deps.lib.bannerView)
    api(Deps.lib.widget_collection)

}


