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
        versionCode = Widget.versionCode
        versionName = Widget.versionName

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

    api(Deps.androidx.viewpager2)
    api(Deps.androidx.appcompat)
    api(Deps.androidx.constraint)
    api(Deps.material)

    implementation(project(":helper"))
}
