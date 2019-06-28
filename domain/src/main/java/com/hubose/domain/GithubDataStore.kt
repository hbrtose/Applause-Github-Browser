package com.hubose.domain

import com.hubose.domain.entity.RepoEntity

interface GithubDataStore {
    suspend fun getRepos(repoOwner: String, number: Int): List<RepoEntity>
}