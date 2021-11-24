package com.virgiliosolano.moviedb.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val API_KEY = "api_key"

class ApiInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(httpUrl)

        return chain.proceed(requestBuilder.build())
    }
}