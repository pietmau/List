package com.pppp.mapper.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Client {
    private var service: Api

    init {
        val interceptor =
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        val okhttpclient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        service = Retrofit.Builder()
            .client(okhttpclient)
            .baseUrl("https://sj9qwuk05k.execute-api.eu-west-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(Api::class.java)
    }

    internal suspend fun getCategories() = service.getCategories()

    internal suspend fun getTags() = service.getTags()

    internal suspend fun getItems() = service.getItems()

}