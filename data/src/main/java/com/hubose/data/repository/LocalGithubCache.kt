package com.hubose.data.repository

import com.hubose.domain.GithubCache
import com.hubose.domain.entity.RepoEntity
import java.lang.IllegalArgumentException

class LocalGithubCache: GithubCache {

    private val repoCache: HashMap<Int, RepoEntity> = LinkedHashMap()

    override suspend fun save(repos: List<RepoEntity>) {
        repos.forEach { repoCache[it.id] = it }
    }

    override suspend fun getRepoById(repoId: Int): RepoEntity {
        return repoCache[repoId] ?: throw IllegalArgumentException("Illegal id")
    }

    override suspend fun filter(phrase: String): List<RepoEntity> {
        return repoCache.values.filter {
            it.name.toLowerCase().contains(phrase.toLowerCase())
        }
    }
}