package com.hubose.applauserepobrowser.common

import android.app.Application
import com.hubose.applauserepobrowser.di.*
import org.koin.android.ext.android.startKoin

class ARBApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(viewModels, dataModule, networkModule, useCases, mappers))
    }
}