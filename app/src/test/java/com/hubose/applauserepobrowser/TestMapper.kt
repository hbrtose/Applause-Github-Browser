package com.hubose.applauserepobrowser

import com.hubose.applauserepobrowser.mapper.RepoEntityToListItemMapper
import com.hubose.domain.common.SampleDataGenerator
import junit.framework.Assert.assertEquals
import org.junit.Test

class TestMapper {

    @Test
    fun testRepoEntityToItemMapper(){
        val repoEntity = SampleDataGenerator.getTestRepoEntity(1)
        val mapper = RepoEntityToListItemMapper()
        val listItem = mapper.mapFrom(repoEntity)
        assertEquals(repoEntity.name, listItem.name)
        assertEquals(repoEntity.id, listItem.id)
        assertEquals(repoEntity.isPrivate, listItem.private)
    }
}