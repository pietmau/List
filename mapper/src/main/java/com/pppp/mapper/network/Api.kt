package com.pppp.mapper.network

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.server.mapping.Mapping
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

    @GET("prod/categories")
    suspend fun getCategories(): List<Category>

    @GET("prod/tags")
    suspend fun getTags(): List<TagImpl>

    @GET("prod/items")
    suspend fun getItems(): List<CheckListItemImpl>

    @POST("prod/mapping")
    suspend fun mapping(@Body mapping: Mapping): Any
}