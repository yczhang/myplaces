package com.example.movielist.network
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newRequestBuilder = original.newBuilder()
            .header("Content-Type", "text/plain")
            .method(original.method, original.body)
        val newRequest = newRequestBuilder.build()
        return chain.proceed(newRequest)
    }
}