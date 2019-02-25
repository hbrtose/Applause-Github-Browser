package com.hubose.applauserepobrowser.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hubose.applauserepobrowser.common.BaseViewModel
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetRepoById

class DetailViewModel(id: Int, getRepoById: GetRepoById): BaseViewModel(){

    private val liveData = MutableLiveData<RepoEntity>()

    fun getLiveData(): LiveData<RepoEntity> = liveData

    init {
        addDisposable(getRepoById.getRepoById(id).subscribe({
            liveData.value = it
        },{
            error.value = it
        }))
    }
}