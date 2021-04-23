package com.example.myplaces.api

import com.example.myplaces.network.getRetrofit
import com.example.myplaces.network.getRetrofit
import com.example.myplaces.viewmodels.PlacesListReponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface PlacesListAPIService {
    @GET("/v3/search/anime")
    fun fetch(@Query("q") keyword:String): Deferred<Response<PlacesListReponse>>
}

object PlacesListAPI {
    val retrofitService: PlacesListAPIService by lazy{
        getRetrofit().create(PlacesListAPIService::class.java)
    }
}