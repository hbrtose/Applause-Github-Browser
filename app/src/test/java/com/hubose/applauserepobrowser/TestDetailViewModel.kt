package com.hubose.applauserepobrowser

import com.hubose.applauserepobrowser.details.DetailViewModel
import com.hubose.data.repository.LocalGithubCache
import com.hubose.domain.GithubCache
import com.hubose.domain.common.SampleDataGenerator
import com.hubose.domain.usecase.GetRepoById
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class TestDetailViewModel {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var githubCache: GithubCache

    @Before
    fun prepare(){
        githubCache = LocalGithubCache()
    }

    @Test
    fun testGetRepoById(){
        val repos = SampleDataGenerator.generateRepoEntityList(5)
        val getRepoById = GetRepoById(githubCache)
        runBlockingTest {
            githubCache.save(repos)
            detailViewModel = DetailViewModel(3, getRepoById)
        }
        val res = SampleDataGenerator.getTestRepoEntity(3)
        assert(res == detailViewModel.getLiveData().value)
    }
}