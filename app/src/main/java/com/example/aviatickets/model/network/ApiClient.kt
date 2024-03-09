package com.example.aviatickets.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    private const val YOUR_BASE_URL = "https://my-json-server.typicode.com/estharossa/fake-api-demo/offer_list"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("YOUR_BASE_URL")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    /**
     * think about performing network request
     */

    val instance = retrofit.create(ApiService::class.java)
}


