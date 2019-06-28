package com.hubose.applauserepobrowser.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hubose.applauserepobrowser.common.BaseViewModel
import com.hubose.applauserepobrowser.entity.RepoListItem
import com.hubose.domain.common.Mapper
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetAllRepos
import com.hubose.domain.usecase.SearchRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ListViewModel(
    private val getAllRepos: GetAllRepos,
    private val searchRepos: SearchRepos,
    private val mapper: Mapper<RepoEntity, RepoListItem>,
    owner: String,
    number: Int
): BaseViewModel(){

    private val listViewState = MutableLiveData<ListViewState>()

    init {
        viewModelScope.launch {
            getRepos(owner, number)
        }
    }

    fun getViewState(): LiveData<ListViewState> = listViewState

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun getRepos(owner: String, number: Int) = withContext(Dispatchers.Default) {
        try {
            getAllRepos.getAllRepos(owner, number).map {
                mapper.observable(it)
            }.apply {
                listViewState.postValue(ListViewState(this))
            }
        } catch (e: Exception) {
            error.postValue(e)
        }
    }

    fun filter(phrase: String){
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    searchRepos.search(phrase).map {
                        mapper.observable(it)
                    }.apply {
                        listViewState.postValue(ListViewState(this))
                    }
                } catch (e: Exception) {
                    error.postValue(e)
                }
            }
        }
    }
}