package com.hubose.domain.usecase

abstract class UseCase<P, T> {

    abstract suspend fun createObservable(data: P? = null): T

    suspend fun observable(withData: P? = null): T {
        return createObservable(withData)
    }

}