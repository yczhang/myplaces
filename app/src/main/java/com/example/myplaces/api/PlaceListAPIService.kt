package com.example.myplaces.api

import com.example.myplaces.network.getRetrofit
import com.example.myplaces.network.getRetrofit
import com.example.myplaces.viewmodels.PlacesListReponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface PlacesListAPIService {
    @GET("/maps/api/place/nearbysearch/json")
    fun fetchplaces(@Query("keyword") keyword:String, @Query("key") key:String, @Query("location") latlon:String, @Query("radius") radius:String): Deferred<Response<PlacesListReponse>>

    @GET("/maps/api/place/photo")
    fun fetchphoto(@Query("key") key:String, @Query("photoreference") photoreference:String, @Query("maxheight") maxheight:String, @Query("maxwidth") maxwidth:String): Deferred<Response<PlacesListReponse>>
}

object PlacesListAPI {
    val retrofitService: PlacesListAPIService by lazy{
        getRetrofit().create(PlacesListAPIService::class.java)
    }
}