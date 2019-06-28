package com.hubose.applauserepobrowser.common

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    val error: SingleLiveEvent<Throwable> = SingleLiveEvent()
}