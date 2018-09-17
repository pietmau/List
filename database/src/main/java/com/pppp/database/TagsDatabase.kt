package com.pppp.database

import com.pppp.entities.Tag
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface TagsDatabase {

    fun getTags(): Single<List<Tag>>
    fun subscribeToTagsAndUpdates(): Observable<List<Tag>>
    fun saveTag(tag: Tag, key: String): Completable
}
