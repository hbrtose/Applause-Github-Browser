package com.hubose.domain

import com.hubose.domain.common.SampleDataGenerator
import com.hubose.domain.entity.RepoEntity
import com.hubose.domain.usecase.GetAllRepos
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
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
        val getAllRepos = GetAllRepos(githubRepository)
        runBlockingTest {
            `when`(githubRepository.getRepos("owner", 1)).thenReturn(repos)
            val repos = getAllRepos.getAllRepos("owner", 1)
            assert(repos.size == 11)
        }
    }
}