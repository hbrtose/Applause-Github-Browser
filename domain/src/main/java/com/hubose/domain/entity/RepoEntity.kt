package com.hubose.domain.entity

data class RepoEntity(
    val id: Int,
    val nodeId: String?,
    val name: String,
    val fullName: String?,
    val isPrivate: Boolean,
    val url: String?,
    val createdAt: String?,
    val lastUpdated: String?,
    val language: String?
)