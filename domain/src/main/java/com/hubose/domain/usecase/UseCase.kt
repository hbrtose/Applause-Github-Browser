package com.hubose.domain.usecase

abstract class UseCase<T> {

    abstract suspend fun createObservable(data: Map<String, Any>? = null): T

    suspend fun observable(withData: Map<String, Any>? = null): T {
        return createObservable(withData)
    }

}