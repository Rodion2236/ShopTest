package com.rodion2236.shopmobiletask.model.flash

data class InfoFlash(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int,
    val image_url: String,
)