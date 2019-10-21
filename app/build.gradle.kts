plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)
    defaultConfig {
        applicationId = Dependencies.Android.applicationId
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
    implementation(Dependencies.Libs.kotlinJdk)
    implementation(Dependencies.Libs.appCompat)
    implementation(Dependencies.Libs.recyclerView)
    implementation(Dependencies.Libs.conctraintLayout)

    implementation(Dependencies.Libs.coreKtx)
    implementation(Dependencies.Libs.fragmentKtx)
    implementation(Dependencies.Libs.retrofit)
    implementation(Dependencies.Libs.retrofitMoshiConverter)
    implementation(Dependencies.Libs.moshiAdapters)
    implementation(Dependencies.Libs.loggingInterceptor)
    implementation(Dependencies.Libs.koin)
    implementation(Dependencies.Libs.koinViewModel)

    implementation(Dependencies.Libs.rxAndroid)
    implementation(Dependencies.Libs.lifecycleExtensions)
    implementation(Dependencies.Libs.lifecycleViewModel)
    implementation(Dependencies.Libs.navigationFragment)
    implementation(Dependencies.Libs.navigationUi)

    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.mockito)
    testImplementation(Dependencies.TestLibs.androidTestRunner)
    testImplementation(Dependencies.TestLibs.androidCore)
    testImplementation(Dependencies.TestLibs.coroutines)

    implementation(project(":data"))
    implementation(project(":domain"))
}
