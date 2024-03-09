package com.example.aviatickets.model.network

import retrofit2.http.GET
import retrofit2.Response
import com.example.aviatickets.model.entity.Offer

interface ApiService {
    @GET("offer_list")
    suspend fun getOffers(): Response<List<Offer>> // Assuming Offer is your data model class
}
