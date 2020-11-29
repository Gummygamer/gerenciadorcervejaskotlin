package com.example.kotlinbeersearch

import retrofit2.Call
import retrofit2.http.GET


interface BeerService {
    @GET("beers")
    fun list() : Call<List<Beer>>
}