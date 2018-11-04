package com.pppp.travelchecklist.api

import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.TagImpl
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient(val url: String) : Client {
    private val retrofit: Retrofit
    private val api: Api

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create<Api>(Api::class.java)
    }

    override fun generateChecklist(tags: List<TagImpl>): Single<CheckListImpl> = api.generateChecklist(tags)
}

interface Client {
    fun generateChecklist(tags: List<TagImpl>): Single<CheckListImpl>
}