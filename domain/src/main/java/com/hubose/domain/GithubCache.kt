package com.hubose.domain

import com.hubose.domain.entity.RepoEntity

interface GithubCache {
    suspend fun save(repos: List<RepoEntity>)
    suspend fun getRepoById(repoId: Int): RepoEntity
    suspend fun filter(phrase: String): List<RepoEntity>
}