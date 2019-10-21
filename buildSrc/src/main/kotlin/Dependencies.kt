private const val kotlinVersion = "1.3.50"
private const val androidGradleVersion = "3.5.1"

private const val roomVersion = "1.0.0"
private const val rxJavaVersion = "2.1.6"
private const val retrofitVersion = "2.6.0"
private const val moshiVersion = "1.5.0"
private const val okHttpInterceptorVersion = "3.12.0"
private const val rxAndroidVersion = "2.0.2"
private const val koinVersion = "1.0.1"
private const val androidxVersion = "1.1.0"
private const val lifecycleExtensionsVersion = "2.1.0"
private const val navigationVersion = "1.0.0"
private const val constraintLayoutVersion = "1.1.3"
private const val recyclerViewVersion = "1.0.0"

private const val junitVersion = "1.0.0"
private const val mockitoVersion = "2.28.2"
private const val androidTestRunnerVersion = "1.1.0"
private const val androidCoreTestingVersion = "2.1.0"
private const val coroutinesTestVersion = "1.3.0-M2"

object Dependencies {
    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        const val safeArgs = "android.arch.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
    }

    object Android {
        const val buildToolsVersion = "29.0.1"
        const val minSdkVersion = 19
        const val targetSdkVersion = 29
        const val compileSdkVersion = 29
        const val applicationId = "com.hubose.applauserepobrowser"
        const val versionCode = 1
        const val versionName = "0.1"
    }

    object Libs {
        const val kotlinJdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        const val appCompat = "androidx.appcompat:appcompat:$androidxVersion"
        const val room = "android.arch.persistence.room:runtime:$roomVersion"
        const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$moshiVersion"
        const val retrofitRxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpInterceptorVersion"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
        const val koin = "org.koin:koin-core:$koinVersion"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
        const val coreKtx = "androidx.core:core-ktx:$androidxVersion"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:$androidxVersion"
        const val lifecycleExtensions = "android.arch.lifecycle:extensions:$lifecycleExtensionsVersion"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleExtensionsVersion"
        const val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "android.arch.navigation:navigation-ui-ktx:$navigationVersion"
        const val conctraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
    }

    object TestLibs {
        const val junit = "androidx.test.ext:junit:$junitVersion"
        const val mockito = "org.mockito:mockito-core:$mockitoVersion"
        const val androidTestRunner = "androidx.test:runner:$androidTestRunnerVersion"
        const val androidCore = "androidx.arch.core:core-testing:$androidCoreTestingVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"
    }
}

