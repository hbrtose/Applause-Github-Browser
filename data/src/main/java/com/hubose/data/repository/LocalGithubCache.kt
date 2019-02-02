package com.hubose.data.repository

import com.hubose.domain.GithubCache
import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single

class LocalGithubCache: GithubCache {

    private val repoCache: HashMap<Int, RepoEntity> = LinkedHashMap()

    override fun save(repos: List<RepoEntity>) {
        repos.forEach { repoCache[it.id] = it }
    }

    override fun getRepoById(repoId: Int): Single<RepoEntity> {
        return Single.just(repoCache[repoId])
    }

    override fun filter(phrase: String): Single<List<RepoEntity>> {
        return Single.just(repoCache.values.filter {
            it.name.toLowerCase().contains(phrase.toLowerCase())
        })
    }
}