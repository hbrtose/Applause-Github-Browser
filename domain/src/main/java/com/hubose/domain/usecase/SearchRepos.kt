package com.hubose.domain.usecase

import com.hubose.domain.GithubCache
import com.hubose.domain.entity.RepoEntity
import java.lang.IllegalArgumentException

class SearchRepos(private val repository: GithubCache): UseCase<String, List<RepoEntity>>() {

    suspend fun search(phrase: String): List<RepoEntity> {
        return observable(phrase)
    }

    override suspend fun createObservable(data: String?): List<RepoEntity> {
        data?.let {
            return repository.filter(it)
        } ?: throw IllegalArgumentException("No phrase provided")
    }
}