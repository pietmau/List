package com.pppp.database

import com.pppp.entities.TagsGroup
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface GroupsDatabase {

    fun getTagGroups(): Single<List<TagsGroup>>
    fun subscribeToGroupsAndUpdates(): Observable<List<TagsGroup>>
    fun saveTagGroup(group: TagsGroup, key: String): Completable
}
