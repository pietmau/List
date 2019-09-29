package com.pppp.travelchecklist.analytics.customanalytics

import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CustomAnalyticsClient {
    suspend fun firstInstall(isDev: Boolean)
}

class IftttCustomAnalyticsClient(private val key: String) : CustomAnalyticsClient {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://maker.ifttt.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service: CustomAnalyticsApi = retrofit.create(CustomAnalyticsApi::class.java)

    override suspend fun firstInstall(isDev: Boolean) = service.onFirstInstall(key, getMap(isDev))

    private fun getMap(dev: Boolean): RequestBody {
        val map = mutableMapOf<String, String>().apply {
            if (dev) {
                set("value1", "IS DEVELOPER")
            }
        }
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(map).toString())
    }
}