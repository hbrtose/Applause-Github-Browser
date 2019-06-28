package com.hubose.domain.usecase

import com.hubose.domain.GithubRepository
import com.hubose.domain.entity.RepoEntity
import java.lang.IllegalArgumentException

class GetAllRepos(private val repository: GithubRepository): UseCase<List<RepoEntity>>() {

    companion object {
        private const val PARAM_OWNER_NAME = "param:ownerName"
        private const val PARAM_NUMBER_OF_REPOS = "param:numberOfRepos"
    }

    suspend fun getAllRepos(ownerName: String, number: Int): List<RepoEntity> {
        val data = HashMap<String, Any>()
        data[PARAM_OWNER_NAME] = ownerName
        data[PARAM_NUMBER_OF_REPOS] = number
        return observable(data)
    }

    override suspend fun createObservable(data: Map<String, Any>?): List<RepoEntity> {
        val ownerName = data?.get(PARAM_OWNER_NAME)
        val number = data?.get(PARAM_NUMBER_OF_REPOS)
        return if (ownerName != null && number != null) {
            repository.getRepos(ownerName as String, number as Int)
        } else {
            throw IllegalArgumentException("No owner or number provided")
        }
    }
}