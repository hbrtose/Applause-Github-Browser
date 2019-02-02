package com.hubose.data.repository

import com.hubose.data.api.Api
import com.hubose.data.mapper.RepoDataToEntityMapper
import com.hubose.domain.GithubDataStore
import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single

class RemoteGithubDataStore(private val api: Api): GithubDataStore {

    private val mapper = RepoDataToEntityMapper()

    override fun getRepos(repoOwner: String, number: Int): Single<List<RepoEntity>> {
        return api.getRepos(repoOwner).map { results ->
            results.take(number).map { mapper.mapFrom(it) }
        }
    }
}