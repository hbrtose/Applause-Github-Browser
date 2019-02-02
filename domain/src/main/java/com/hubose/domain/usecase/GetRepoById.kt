package com.hubose.domain.usecase

import com.hubose.domain.GithubCache
import com.hubose.domain.common.Transformer
import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single
import java.lang.IllegalArgumentException

class GetRepoById(transformer: Transformer<RepoEntity>,
                  private val repository: GithubCache): UseCase<RepoEntity>(transformer) {

    companion object {
        private const val PARAM_REPO_ID = "param:repoId"
    }

    fun getRepoById(repoId: Int): Single<RepoEntity> {
        val data = HashMap<String, Int>()
        data[PARAM_REPO_ID] = repoId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Single<RepoEntity> {
        val repoId = data?.get(PARAM_REPO_ID)
        repoId?.let {
            return repository.getRepoById(it as Int)
        } ?: return Single.error { IllegalArgumentException("No ID provided") }
    }
}