package com.hubose.domain

import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single

interface GithubRepository {
    fun getRepos(ownerName: String, number: Int): Single<List<RepoEntity>>
    fun getRepoById(repoId: Int): Single<RepoEntity>
    fun filter(phrase: String): Single<List<RepoEntity>>
}