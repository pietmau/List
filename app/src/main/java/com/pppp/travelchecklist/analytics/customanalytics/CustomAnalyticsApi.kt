package com.pppp.travelchecklist.analytics.customanalytics

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CustomAnalyticsApi {
    @POST("trigger/first_install/with/key/{key}")
    suspend fun onFirstInstall(@Path("key") user: String, @Body value: RequestBody)
}
