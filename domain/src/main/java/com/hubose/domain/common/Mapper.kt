package com.hubose.domain.common

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T

    fun observable(from: E): T {
        return mapFrom(from)
    }

    fun observable(from: List<E>): List<T> {
        return from.map { mapFrom(it) }
    }
}