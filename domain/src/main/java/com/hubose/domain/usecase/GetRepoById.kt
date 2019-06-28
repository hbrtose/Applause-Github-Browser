package com.hubose.domain.usecase

import com.hubose.domain.GithubCache
import com.hubose.domain.entity.RepoEntity
import java.lang.IllegalArgumentException

class GetRepoById(private val repository: GithubCache): UseCase<RepoEntity>() {

    companion object {
        private const val PARAM_REPO_ID = "param:repoId"
    }

    suspend fun getRepoById(repoId: Int): RepoEntity {
        val data = HashMap<String, Int>()
        data[PARAM_REPO_ID] = repoId
        return observable(data)
    }

    override suspend fun createObservable(data: Map<String, Any>?): RepoEntity {
        val repoId = data?.get(PARAM_REPO_ID)
        repoId?.let {
            return repository.getRepoById(it as Int)
        } ?: throw IllegalArgumentException("No ID provided")
    }
}