package com.hubose.applauserepobrowser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hubose.applauserepobrowser.details.DetailViewModel
import com.hubose.data.repository.LocalGithubCache
import com.hubose.domain.GithubCache
import com.hubose.domain.common.SampleDataGenerator
import com.hubose.domain.common.TestTransformer
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetRepoById
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestDetailViewModel {

    companion object {
        @ClassRule
        @JvmField
        var schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var githubCache: GithubCache
    private lateinit var detailObserver: Observer<RepoEntity>
    private lateinit var errorObserver: Observer<Throwable>

    @Before
    fun prepare(){
        githubCache = LocalGithubCache()
        val getRepoById = GetRepoById(TestTransformer(), githubCache)
        detailViewModel = DetailViewModel(getRepoById)
        detailObserver = Mockito.mock(Observer::class.java) as Observer<RepoEntity>
        errorObserver = Mockito.mock(Observer::class.java) as Observer<Throwable>
        detailViewModel.getLiveData().observeForever(detailObserver)
        detailViewModel.error.observeForever(errorObserver)
    }

    @Test
    fun testGetRepoById(){
        val repos = SampleDataGenerator.generateRepoEntityList(5)
        githubCache.save(repos)
        val res = SampleDataGenerator.getTestRepoEntity(3)
        detailViewModel.getRepo(3)
        verifyZeroInteractions(errorObserver)
        verify(detailObserver).onChanged(res)
    }
}