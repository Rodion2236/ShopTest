package com.rodion2236.shopmobiletask.repository

import com.rodion2236.shopmobiletask.api.Retrofit
import com.rodion2236.shopmobiletask.model.flash.Flash
import com.rodion2236.shopmobiletask.model.latest.Latest

class ShopRepository {

    private val api = Retrofit.api

    suspend fun getLatest(): Latest {
        return api.getLatest()
    }

    suspend fun getFlash(): Flash {
        return api.getFlash()
    }
}