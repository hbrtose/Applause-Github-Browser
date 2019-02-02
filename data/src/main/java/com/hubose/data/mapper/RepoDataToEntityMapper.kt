package com.hubose.data.mapper

import com.hubose.data.entity.RepoData
import com.hubose.domain.common.Mapper
import com.hubose.domain.entity.RepoEntity

class RepoDataToEntityMapper: Mapper<RepoData, RepoEntity>() {
    override fun mapFrom(from: RepoData): RepoEntity {
        return RepoEntity(from.id, from.nodeId, from.name, from.fullName, from.private, from.htmlUrl, from.createdAt.toString(),
            from.updatedAt.toString(), from.language)
    }
}