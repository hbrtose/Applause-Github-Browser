package com.hubose.domain

import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single

interface GithubCache {
    fun save(repos: List<RepoEntity>)
    fun getRepoById(repoId: Int): Single<RepoEntity>
    fun filter(phrase: String): Single<List<RepoEntity>>
}