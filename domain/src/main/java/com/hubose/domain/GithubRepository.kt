package com.hubose.domain

import com.hubose.domain.entity.RepoEntity

interface GithubRepository {
    suspend fun getRepos(ownerName: String, number: Int): List<RepoEntity>
    suspend fun getRepoById(repoId: Int): RepoEntity
    suspend fun filter(phrase: String): List<RepoEntity>
}