package com.hubose.domain

import com.hubose.domain.common.SampleDataGenerator
import com.hubose.domain.common.TestTransformer
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetAllRepos
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class UseCaseTests {

    private lateinit var repos: List<RepoEntity>
    private lateinit var githubRepository: GithubRepository

    @Before
    fun prepare() {
        repos = SampleDataGenerator.generateRepoEntityList(10)
        githubRepository = mock(GithubRepository::class.java)
    }

    @Test
    fun testGetAllRepos(){
        val getAllRepos = GetAllRepos(TestTransformer(), githubRepository)

        `when`(githubRepository.getRepos(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(Single.just(repos))

        getAllRepos.getAllRepos("owner", 1).test()
            .assertValue { result -> result.size == 11 }
            .assertComplete()
    }
}