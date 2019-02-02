package com.hubose.data.api

import com.hubose.data.entity.RepoData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") username: String): Single<List<RepoData>>
}