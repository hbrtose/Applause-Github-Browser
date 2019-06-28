package com.hubose.applauserepobrowser.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hubose.applauserepobrowser.common.BaseViewModel
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetRepoById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DetailViewModel(private val id: Int, private val getRepoById: GetRepoById): BaseViewModel(){

    private val liveData = MutableLiveData<RepoEntity>()

    fun getLiveData(): LiveData<RepoEntity> = liveData

    init {
        viewModelScope.launch {
            getRepo()
        }
    }

    private suspend fun getRepo() = withContext(Dispatchers.Default) {
        try {
            getRepoById.getRepoById(id).apply {
                liveData.postValue(this)
            }
        } catch (e: Exception) {
            error.postValue(e)
        }
    }
}