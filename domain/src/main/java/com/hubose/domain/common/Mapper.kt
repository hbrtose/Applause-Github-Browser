package com.hubose.domain.common

import io.reactivex.Single

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T

    fun observable(from: E): Single<T> {
        return Single.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<E>): Single<List<T>> {
        return Single.fromCallable { from.map { mapFrom(it) } }
    }
}