package com.faisal.dc.apprise.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    val BASE_URL = "http://www.omdbapi.com"
    val API_KEY= "2088fa00"

    fun getInstance(): Retrofit {

         val interceptor : HttpLoggingInterceptor by lazy {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }

         val httpClient : OkHttpClient by lazy {
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        }
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}