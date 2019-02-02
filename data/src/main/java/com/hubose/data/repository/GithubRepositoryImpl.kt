package com.hubose.data.repository

import com.hubose.domain.GithubCache
import com.hubose.domain.GithubDataStore
import com.hubose.domain.GithubRepository
import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single

class GithubRepositoryImpl(private val remoteData: GithubDataStore,
                           private val localData: GithubCache): GithubRepository {

    override fun getRepos(ownerName: String, number: Int): Single<List<RepoEntity>> {
        return remoteData.getRepos(ownerName, number).doOnSuccess {
            localData.save(it)
        }
    }

    override fun getRepoById(repoId: Int): Single<RepoEntity> {
        return localData.getRepoById(repoId)
    }

    override fun filter(phrase: String): Single<List<RepoEntity>> {
        return localData.filter(phrase)
    }
}