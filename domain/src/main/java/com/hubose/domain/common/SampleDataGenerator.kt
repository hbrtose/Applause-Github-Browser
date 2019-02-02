package com.hubose.domain.common

import com.hubose.domain.entity.RepoEntity
import java.util.*

class SampleDataGenerator {
    companion object {
        fun getTestRepoEntity(id: Int): RepoEntity {
            return RepoEntity(
                id = id,
                nodeId = "node$id",
                name = "Repo$id",
                fullName = "applaudeOSS/Repo$id",
                isPrivate = (id%2 == 0),
                url = "http://github.com/repo$id",
                createdAt = Date().toString(),
                lastUpdated = Date().toString(),
                language = "Java")
        }

        fun generateRepoEntityList(n: Int): List<RepoEntity> {
            return (0..n).map { getTestRepoEntity(it) }
        }
    }
}