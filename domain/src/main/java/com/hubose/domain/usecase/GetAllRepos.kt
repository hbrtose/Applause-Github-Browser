package com.hubose.domain.usecase

import com.hubose.domain.GithubRepository
import com.hubose.domain.common.Transformer
import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single
import java.lang.IllegalArgumentException

class GetAllRepos(transformer: Transformer<List<RepoEntity>>,
                  private val repository: GithubRepository): UseCase<List<RepoEntity>>(transformer) {

    companion object {
        private const val PARAM_OWNER_NAME = "param:ownerName"
        private const val PARAM_NUMBER_OF_REPOS = "param:numberOfRepos"
    }

    fun getAllRepos(ownerName: String, number: Int): Single<List<RepoEntity>> {
        val data = HashMap<String, Any>()
        data[PARAM_OWNER_NAME] = ownerName
        data[PARAM_NUMBER_OF_REPOS] = number
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Single<List<RepoEntity>> {
        val ownerName = data?.get(PARAM_OWNER_NAME)
        val number = data?.get(PARAM_NUMBER_OF_REPOS)
        return if (ownerName != null && number != null) {
            repository.getRepos(ownerName as String, number as Int)
        } else {
            Single.error { IllegalArgumentException("No owner or number provided") }
        }
    }
}