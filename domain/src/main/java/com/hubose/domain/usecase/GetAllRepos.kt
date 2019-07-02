package com.hubose.domain.usecase

import com.hubose.domain.GithubRepository
import com.hubose.domain.entity.RepoEntity
import java.lang.IllegalArgumentException

class GetAllRepos(private val repository: GithubRepository): UseCase<GetAllRepos.GetAllReposParams, List<RepoEntity>>() {

    suspend fun getAllRepos(ownerName: String, number: Int): List<RepoEntity> {
        return observable(GetAllReposParams(ownerName, number))
    }

    override suspend fun createObservable(data: GetAllReposParams?): List<RepoEntity> {
        data?.let {
            return repository.getRepos(data.ownerName, data.numberOfRepos)
        } ?: throw IllegalArgumentException("No owner or number provided")
    }

    data class GetAllReposParams(
        val ownerName: String,
        val numberOfRepos: Int
    )
}