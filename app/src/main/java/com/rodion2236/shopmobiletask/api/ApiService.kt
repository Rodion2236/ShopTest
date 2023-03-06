package com.rodion2236.shopmobiletask.api

import com.rodion2236.shopmobiletask.model.flash.Flash
import com.rodion2236.shopmobiletask.model.latest.Latest
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getFlash(): Response<Flash>

    @GET("/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getLatest(): Response<Latest>
}