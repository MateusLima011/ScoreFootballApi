package com.mgt.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mgt.data.remote.RemoteApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private fun buildInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(interceptor)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("x-rapidapi-host", "v3.football.api-sports.io")
                .build()
            chain.proceed(newRequest)
        }
    return interceptor
}


private val client = OkHttpClient.Builder().apply {
    addInterceptor(buildInterceptor())
}.build()

fun buildRetrofit(): Retrofit {
    val contentType = "application/json".toMediaType()

    return Retrofit.Builder()
        .client(client)
        .baseUrl("https://v3.football.api-sports.io/")
        .addConverterFactory(Json {
            isLenient = true
            ignoreUnknownKeys = true
        }.asConverterFactory(contentType))
        .build()
}

fun buildApiService(): RemoteApiService =
    buildRetrofit().create(RemoteApiService::class.java)
