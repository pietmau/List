package com.pppp.travelchecklist.api

import com.pppp.entities.pokos.CheckList
import com.pppp.entities.pokos.Tag
import io.reactivex.Single
import retrofit2.http.GET


interface Api {

    @GET("foo")
    fun generateChecklist(tags: List<Tag>): Single<CheckList>
}