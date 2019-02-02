package com.hubose.applauserepobrowser.mapper

import com.hubose.applauserepobrowser.entity.RepoListItem
import com.hubose.domain.common.Mapper
import com.hubose.domain.entity.RepoEntity

class RepoEntityToListItemMapper: Mapper<RepoEntity, RepoListItem>() {

    override fun mapFrom(from: RepoEntity): RepoListItem {
        return RepoListItem(from.id, from.name, from.isPrivate)
    }
}