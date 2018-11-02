package com.pppp.travelchecklist.api

import com.pppp.entities.pokos.CheckList
import com.pppp.entities.pokos.Tag
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

    override fun generateChecklist(tags: List<Tag>): Single<CheckList> = api.generateChecklist(tags)
}

interface Client {
    fun generateChecklist(tags: List<Tag>): Single<CheckList>
}