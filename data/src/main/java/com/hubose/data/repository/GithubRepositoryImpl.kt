package com.hubose.data.repository

import com.hubose.domain.GithubCache
import com.hubose.domain.GithubDataStore
import com.hubose.domain.GithubRepository
import com.hubose.domain.entity.RepoEntity

class GithubRepositoryImpl(private val remoteData: GithubDataStore,
                           private val localData: GithubCache): GithubRepository {

    override suspend fun getRepos(ownerName: String, number: Int): List<RepoEntity> {
        return remoteData.getRepos(ownerName, number).apply {
            localData.save(this)
        }
    }

    override suspend fun getRepoById(repoId: Int): RepoEntity {
        return localData.getRepoById(repoId)
    }

    override suspend fun filter(phrase: String): List<RepoEntity> {
        return localData.filter(phrase)
    }
}