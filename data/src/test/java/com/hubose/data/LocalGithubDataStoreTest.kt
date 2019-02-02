package com.hubose.data

import com.hubose.data.repository.LocalGithubCache
import com.hubose.domain.GithubCache
import com.hubose.domain.common.SampleDataGenerator
import org.junit.Before
import org.junit.Test

class LocalGithubDataStoreTest {

    private lateinit var repoCache: GithubCache

    @Before
    fun before() {
        repoCache = LocalGithubCache()
        repoCache.save(SampleDataGenerator.generateRepoEntityList(10))
    }

    @Test
    fun testFilter() {
        repoCache.filter("1").test()
            .assertValue { list -> list.size == 2 }
            .assertComplete()
    }

    @Test
    fun testGetById() {
        repoCache.getRepoById(1).test()
            .assertValue { list -> list.id == 1 }
            .assertValue { repo -> repo.name == "Repo1" }
            .assertComplete()
    }
}