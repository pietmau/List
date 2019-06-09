package com.pppp.travelchecklist.api

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckList
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.TagImpl
import com.pppp.entities.pokos.TagsGroupImpl
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("prod/items/bytag")
    fun generateChecklist(@Body tags: List<TagImpl>): Single<List<CategoryImpl>>

    @GET("prod/tags-groups")
    fun getTagsGroup(): Single<List<TagsGroupImpl>>
}