package com.pppp.database

import com.pppp.entities.pokos.TagsGroupImpl
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface GroupsDatabase {

    fun getTagGroups(): Single<List<TagsGroupImpl>>
    fun subscribeToGroupsAndUpdates(): Observable<List<TagsGroupImpl>>
    fun saveTagGroup(group: TagsGroupImpl, key: String): Completable
}
