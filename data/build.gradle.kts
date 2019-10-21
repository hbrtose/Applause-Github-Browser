plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        versionCode = Dependencies.Android.versionCode
        versionName = Dependencies.Android.versionName

        testInstrumentationRunner = Dependencies.BuildPlugins.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Dependencies.Libs.appCompat)
    implementation(Dependencies.Libs.kotlinJdk)
    implementation(Dependencies.Libs.retrofit)
    implementation(Dependencies.Libs.retrofitMoshiConverter)
    implementation(Dependencies.Libs.room)

    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.mockito)
    testImplementation(Dependencies.TestLibs.androidTestRunner)
    testImplementation(Dependencies.TestLibs.androidCore)
    testImplementation(Dependencies.TestLibs.coroutines)

    implementation(project(":domain"))
}
