package com.pppp.travelchecklist.api

import com.pietrantuono.entities.TagsGroup
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.TagImpl
import com.pppp.entities.pokos.TagsGroupImpl
import io.reactivex.Single
import retrofit2.http.GET


interface Api {

    @GET("foo")
    fun generateChecklist(tags: List<TagImpl>): Single<CheckListImpl>

    @GET("prod/tags-groups")
    fun getTagsGroup(): Single<TagsGroupImpl>
}