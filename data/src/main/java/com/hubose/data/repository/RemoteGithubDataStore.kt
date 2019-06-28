package com.hubose.data.repository

import com.hubose.data.api.Api
import com.hubose.data.mapper.RepoDataToEntityMapper
import com.hubose.domain.GithubDataStore
import com.hubose.domain.entity.RepoEntity

class RemoteGithubDataStore(private val api: Api): GithubDataStore {

    private val mapper = RepoDataToEntityMapper()

    override suspend fun getRepos(repoOwner: String, number: Int): List<RepoEntity> {
        return api.getRepos(repoOwner).take(number).map { mapper.mapFrom(it) }
    }
}