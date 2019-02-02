package com.hubose.applauserepobrowser.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hubose.applauserepobrowser.common.BaseViewModel
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetRepoById

class DetailViewModel(private val getRepoById: GetRepoById): BaseViewModel(){

    private val liveData = MutableLiveData<RepoEntity>()

    fun getLiveData(): LiveData<RepoEntity> = liveData

    fun getRepo(id: Int) {
        addDisposable(getRepoById.getRepoById(id).subscribe({
            liveData.value = it
        },{
            error.value = it
        }))
    }
}