package com.hubose.data.api

import com.hubose.data.entity.RepoData
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): List<RepoData>
}