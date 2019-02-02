package com.hubose.applauserepobrowser.di

import com.hubose.applauserepobrowser.BuildConfig
import com.hubose.data.api.Api
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor


val networkModule = module(createOnStart = true) {
    single { createHttpClient() }
    single { createApi<Api>(get()) }
}

fun createHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
    provideInterceptors().forEach { builder.addInterceptor(it) }
    return builder.build()
}

inline fun <reified T> createApi(okHttpClient: OkHttpClient): T {
    val moshi = Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe()).build()
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

fun provideInterceptors(): List<Interceptor> {
    val interceptors = arrayListOf<Interceptor>()
    if(BuildConfig.DEBUG){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptors.add(interceptor)
    }
    return interceptors
}