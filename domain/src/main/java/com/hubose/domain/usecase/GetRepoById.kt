package com.hubose.domain.usecase

import com.hubose.domain.GithubCache
import com.hubose.domain.entity.RepoEntity
import java.lang.IllegalArgumentException

class GetRepoById(private val repository: GithubCache): UseCase<Int, RepoEntity>() {

    suspend fun getRepoById(repoId: Int): RepoEntity {
        return observable(repoId)
    }

    override suspend fun createObservable(data: Int?): RepoEntity {
        data?.let {
            return repository.getRepoById(it)
        } ?: throw IllegalArgumentException("No ID provided")
    }
}