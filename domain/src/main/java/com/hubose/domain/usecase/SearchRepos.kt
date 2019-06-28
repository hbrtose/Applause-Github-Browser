package com.hubose.domain.usecase

import com.hubose.domain.GithubCache
import com.hubose.domain.entity.RepoEntity
import java.lang.IllegalArgumentException

class SearchRepos(private val repository: GithubCache): UseCase<List<RepoEntity>>() {

    companion object {
        private const val PARAM_PHRASE = "param:phrase"
    }

    suspend fun search(phrase: String): List<RepoEntity> {
        val data = HashMap<String, String>()
        data[PARAM_PHRASE] = phrase
        return observable(data)
    }

    override suspend fun createObservable(data: Map<String, Any>?): List<RepoEntity> {
        val phrase = data?.get(PARAM_PHRASE)
        phrase?.let {
            return repository.filter(it as String)
        } ?: throw IllegalArgumentException("No phrase provided")
    }
}