package com.hubose.domain.usecase

import com.hubose.domain.GithubCache
import com.hubose.domain.common.Transformer
import com.hubose.domain.entity.RepoEntity
import io.reactivex.Single
import java.lang.IllegalArgumentException

class SearchRepos(transformer: Transformer<List<RepoEntity>>,
                  private val repository: GithubCache): UseCase<List<RepoEntity>>(transformer) {

    companion object {
        private const val PARAM_PHRASE = "param:phrase"
    }

    fun search(phrase: String): Single<List<RepoEntity>> {
        val data = HashMap<String, String>()
        data[PARAM_PHRASE] = phrase
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Single<List<RepoEntity>> {
        val phrase = data?.get(PARAM_PHRASE)
        phrase?.let {
            return repository.filter(it as String)
        } ?: return Single.error { IllegalArgumentException("No phrase provided") }
    }
}