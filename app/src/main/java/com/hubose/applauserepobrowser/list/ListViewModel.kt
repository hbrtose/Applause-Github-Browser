package com.hubose.applauserepobrowser.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hubose.applauserepobrowser.common.BaseViewModel
import com.hubose.applauserepobrowser.entity.RepoListItem
import com.hubose.domain.common.Mapper
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetAllRepos
import com.hubose.domain.usecase.SearchRepos

class ListViewModel(
    private val getAllRepos: GetAllRepos,
    private val searchRepos: SearchRepos,
    private val mapper: Mapper<RepoEntity, RepoListItem>,
    owner: String,
    number: Int
): BaseViewModel(){

    private val listViewState = MutableLiveData<ListViewState>()

    init {
        getRepos(owner, number)
    }

    fun getViewState(): LiveData<ListViewState> = listViewState

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getRepos(owner: String, number: Int) {
        addDisposable(getAllRepos.getAllRepos(owner, number)
            .flatMap { mapper.observable(it) }
            .subscribe({
                listViewState.value = ListViewState(it)
            },{
                error.value = it
            }))
    }

    fun filter(phrase: String){
        addDisposable(searchRepos.search(phrase)
            .flatMap { mapper.observable(it) }
            .subscribe({
                listViewState.value = ListViewState(it)
            },{
                error.value = it
            }))
    }
}