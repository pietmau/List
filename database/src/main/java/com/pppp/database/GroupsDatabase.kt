package com.pppp.database

import com.pietrantuono.entities.TagsGroup
import com.pppp.entities.pokos.TagsGroupImpl
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface GroupsDatabase {

    fun getTagGroups(): Single<out List<TagsGroup>>
    fun subscribeToGroupsAndUpdates(): Observable<List<TagsGroup>>
    fun saveTagGroup(group: TagsGroupImpl, key: String): Completable
}
