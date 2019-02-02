package com.hubose.domain

import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single

interface GithubDataStore {
    fun getRepos(repoOwner: String, number: Int): Single<List<RepoEntity>>
}