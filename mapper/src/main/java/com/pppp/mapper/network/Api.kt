package com.pppp.mapper.network

import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TagImpl
import retrofit2.http.GET


interface Api {

    @GET("prod/categories")
    suspend fun getCategories(): List<CategoryImpl>

    @GET("prod/tags")
    suspend fun getTags(): List<TagImpl>

    @GET("prod/items")
    suspend fun getItems(): List<CheckListItemImpl>
}