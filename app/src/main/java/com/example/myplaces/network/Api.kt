package com.example.movielist.network

import com.example.myplaces.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//TODO: Get this from the properties file.
private const val BASE_URL = BuildConfig.BASE_URL

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val client = OkHttpClient().newBuilder()
    .addInterceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        response
    }
    .build()


private val retrofit = Retrofit.Builder()
  .addConverterFactory(MoshiConverterFactory.create(moshi))
  .addCallAdapterFactory(CoroutineCallAdapterFactory())
  .baseUrl(BASE_URL)
    .client(client)
    .build()

fun getRetrofit(): Retrofit{
    return retrofit
}
