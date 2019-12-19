package com.pppp.travelchecklist.api

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TagsGroup
import com.pppp.entities.pokos.RoomTag
import com.pppp.entities.pokos.TagsGroupImpl
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(url: String) : Client {
    private val retrofit: Retrofit
    private val api: Api

    init {
        val interceptor =
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        val okhttpclient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder()
            .client(okhttpclient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create<Api>(Api::class.java)
    }

    override fun generateChecklist(tags: List<RoomTag>): Single<out List<Category>> = api.generateChecklist(tags)

    override fun getTagsGroup(): Single<out List<TagsGroup>> = api.getTagsGroup()

    override fun getTagsGroupCall() = api.getTagsGroupCall()

}

interface Client {
    fun generateChecklist(tags: List<RoomTag>): Single<out List<Category>>

    fun getTagsGroup(): Single<out List<TagsGroup>>

    fun getTagsGroupCall(): Call<List<TagsGroupImpl>>
}