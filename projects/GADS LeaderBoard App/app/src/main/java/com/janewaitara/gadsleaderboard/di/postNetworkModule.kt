package com.janewaitara.gadsleaderboard.di

import com.janewaitara.gadsleaderboard.networking.PostRemoteApi
import com.janewaitara.gadsleaderboard.networking.PostRemoteApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val POST_BASE_URL = "https://docs.google.com/forms/d/e/"

val postNetworkModule = module {
    //Logging Interceptor
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }

    //OKHTTPClient class
    single {
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .addInterceptor(get<HttpLoggingInterceptor>()) //using get with a type to let koin know which interceptor to get
            .build()

    }
    //moshi instance
    single{
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    //retrofit class
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(POST_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get())) // automatically parses the json and gives it the type that you need and asLenient gives a more forgiving parser
            .build()
    }

    //create RemoteApi
    single {
        get<Retrofit>().create(PostRemoteApiService::class.java)
    }
}

val postRemoteApiModule = module{
    single { PostRemoteApi(get()) }
}