package com.pppp.database

import com.pppp.entities.pokos.TagImpl
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface TagsDatabase {

    fun getTags(): Single<List<TagImpl>>
    fun subscribeToTagsAndUpdates(): Observable<List<TagImpl>>
    fun saveTag(tag: TagImpl, key: String): Completable
}
