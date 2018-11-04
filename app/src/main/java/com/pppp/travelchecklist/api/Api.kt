package com.pppp.travelchecklist.api

import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.TagImpl
import io.reactivex.Single
import retrofit2.http.GET


interface Api {

    @GET("foo")
    fun generateChecklist(tags: List<TagImpl>): Single<CheckListImpl>
}