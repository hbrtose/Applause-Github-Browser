package com.hubose.applauserepobrowser

import androidx.lifecycle.Observer
import com.hubose.applauserepobrowser.entity.RepoListItem
import com.hubose.applauserepobrowser.list.ListViewModel
import com.hubose.applauserepobrowser.list.ListViewState
import com.hubose.applauserepobrowser.mapper.RepoEntityToListItemMapper
import com.hubose.data.repository.LocalGithubCache
import com.hubose.domain.GithubCache
import com.hubose.domain.GithubRepository
import com.hubose.domain.common.Mapper
import com.hubose.domain.common.SampleDataGenerator
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetAllRepos
import com.hubose.domain.usecase.SearchRepos
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class TestListViewModel {

    private lateinit var listViewModel: ListViewModel
    private lateinit var githubRepository: GithubRepository
    private lateinit var githubCache: GithubCache
    private lateinit var errorObserver: Observer<Throwable>
    private lateinit var listObserver: Observer<ListViewState>
    private lateinit var mapper: Mapper<RepoEntity, RepoListItem>
    private val OWNER = "owner"
    private val NUMBER = 1

    @Before
    fun prepare(){
        githubCache = LocalGithubCache()
        githubRepository = mock(GithubRepository::class.java)
        val getAllRepos = GetAllRepos(githubRepository)
        val searchRepos = SearchRepos(githubCache)
        mapper = RepoEntityToListItemMapper()
        val repos = SampleDataGenerator.generateRepoEntityList(5)
        runBlockingTest {
            `when`(githubRepository.getRepos(OWNER, NUMBER)).thenReturn(repos)
            listViewModel = ListViewModel(getAllRepos, searchRepos, mapper, OWNER, NUMBER)
        }
        listObserver = mock(Observer::class.java) as Observer<ListViewState>
        errorObserver = mock(Observer::class.java) as Observer<Throwable>
        listViewModel.getViewState().observeForever(listObserver)
        listViewModel.error.observeForever(errorObserver)
    }

    @Test
    fun testInit(){
        val repos = SampleDataGenerator.generateRepoEntityList(5)
        verifyNoMoreInteractions(errorObserver)
        verify(listObserver).onChanged(ListViewState(repos.map { mapper.mapFrom(it) }))
    }

    @Test
    fun search(){
        val repos = SampleDataGenerator.generateRepoEntityList(5)
        runBlockingTest {
            githubCache.save(repos)
        }
        val result = SampleDataGenerator.getTestRepoEntity(3)
        listViewModel.filter("3")
        verifyZeroInteractions(errorObserver)
        verify(listObserver).onChanged(ListViewState(listOf(mapper.mapFrom(result))))
    }
}