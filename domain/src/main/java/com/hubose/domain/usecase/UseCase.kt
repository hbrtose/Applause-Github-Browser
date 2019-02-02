package com.hubose.domain.usecase

import com.hubose.domain.common.Transformer
import io.reactivex.Single

abstract class UseCase<T>(private val transformer: Transformer<T>) {

    abstract fun createObservable(data: Map<String, Any>? = null): Single<T>

    fun observable(withData: Map<String, Any>? = null): Single<T> {
        return createObservable(withData).compose(transformer)
    }

}