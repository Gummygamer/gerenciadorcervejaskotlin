package com.example.kotlinbeersearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    val retrofit = Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    fun BeerService () : BeerService{
        return retrofit.create(BeerService::class.java)
    }
}