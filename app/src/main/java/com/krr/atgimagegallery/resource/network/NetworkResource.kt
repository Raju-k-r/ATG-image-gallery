package com.krr.atgimagegallery.resource.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URI = "https://api.flickr.com/services/rest/"

object NetWorkResource{

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val api by lazy {
        retrofit.create(NetworkApi::class.java)
    }

    val _api: NetworkApi = api

}
