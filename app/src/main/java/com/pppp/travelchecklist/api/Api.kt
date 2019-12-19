package com.pppp.travelchecklist.api

import com.pppp.entities.pokos.RoomTag
import com.pppp.entities.pokos.TagsGroupImpl
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("prod/mock/bytag")
    fun generateChecklist(@Body tags: List<RoomTag>): Single<List<NetworkCategory>>

    @GET("prod/mock/tags-group")
    fun getTagsGroup(): Single<List<TagsGroupImpl>>

    @GET("prod/mock/tags-group")
    fun getTagsGroupCall(): Call<List<TagsGroupImpl>>
}