package com.janewaitara.gadsleaderboard.di

import com.janewaitara.gadsleaderboard.BuildConfig
import com.janewaitara.gadsleaderboard.networking.GetRemoteApi
import com.janewaitara.gadsleaderboard.networking.GetRemoteApiService
import com.janewaitara.gadsleaderboard.networking.PostRemoteApi
import com.janewaitara.gadsleaderboard.networking.PostRemoteApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val GET_BASE_URL = "https://gadsapi.herokuapp.com"
const val POST_BASE_URL = "https://docs.google.com/forms/d/e/"

val networkModule = module(override = true){
/*    //Logging Interceptor
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }

    //OKHTTPClient class
    single{
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
    single{
        Retrofit.Builder()
            .client(get())
            .baseUrl(GET_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get())) // automatically parses the json and gives it the type that you need and asLenient gives a more forgiving parser
            .build()
    }
    //create RemoteApi
    single {
        get<Retrofit>().create(GetRemoteApiService::class.java)
    }*/
    single ( named("GET_BASE_URL")){GET_BASE_URL}
    single ( named("POST_BASE_URL")){ POST_BASE_URL}
    single { provideOkHttpClient() }
    single { provideMoshi() }
    single { buildLeadersApiService(get(named("GET_BASE_URL")))}
    single { buildFormApiService(get(named("POST_BASE_URL"))) }
}
val getRemoteApiModule = module(true) {
    single( override = true) { GetRemoteApi(get()) }
}

val postRemoteApiModule = module(override = true){
    single(named("postNetworkModule")){ PostRemoteApi(get()) }
}

fun provideOkHttpClient() =  if(BuildConfig.DEBUG){

    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
        .readTimeout(5, TimeUnit.MINUTES) // read timeout
        .addInterceptor(loggingInterceptor) //using get with a type to let koin know which interceptor to get
        .build()
}else{
    OkHttpClient.Builder().build()

}

fun provideMoshi(): Moshi =
    Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun provideRetrofit(baseUrl: String): Retrofit =
    Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi())) // automatically parses the json and gives it the type that you need and asLenient gives a more forgiving parser
        .build()

private fun buildLeadersApiService(getBaseUrl: String) = provideRetrofit(getBaseUrl).create(GetRemoteApiService::class.java)
private fun buildFormApiService(postBaseUrl: String) = provideRetrofit(postBaseUrl).create(PostRemoteApiService::class.java)
