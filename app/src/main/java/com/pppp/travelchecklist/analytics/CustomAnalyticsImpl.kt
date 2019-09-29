package com.pppp.travelchecklist.analytics

import android.content.Context
import okhttp3.MediaType

import okhttp3.RequestBody

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.http.Path
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import kotlin.coroutines.CoroutineContext

class CustomAnalyticsImpl(private val context: Context, private val customAnalyticsClient: CustomAnalyticsClient) : CustomAnalytics {

    override fun logFirstInstall() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                customAnalyticsClient.firstInstall(true)
            }
        }
    }
}

interface CustomAnalyticsApi {

    @POST("trigger/first_install/with/key/{key}")
    suspend fun onFirstInstall(@Path("key") user: String, @Body value: RequestBody)
}

class CustomAnalyticsClientImpl(private val key: String) : CustomAnalyticsClient {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://maker.ifttt.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service: CustomAnalyticsApi = retrofit.create(CustomAnalyticsApi::class.java)

    override suspend fun firstInstall(isDev: Boolean) = service.onFirstInstall(key, getMap(isDev))

    private fun getMap(dev: Boolean): RequestBody {
        val map = mutableMapOf<String, String>().apply {
            if (dev) {
                set("value1", "is_dev")
            }
        }
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(map).toString())
    }
}

interface CustomAnalyticsClient {

    suspend fun firstInstall(isDev: Boolean)
}
