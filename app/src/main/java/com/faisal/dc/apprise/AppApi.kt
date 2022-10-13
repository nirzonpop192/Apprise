package com.faisal.dc.apprise

import com.faisal.dc.apprise.model.MovieResponse
import com.faisal.dc.apprise.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET

import retrofit2.http.Query


//@GET("?i=tt3896198&apikey=2088fa00")
interface AppApi {
    @GET("/")
    suspend fun getMovie(@Query("i") imd_id:String, @Query("apikey")api_key:String) : Response<MovieResponse>
    @GET("/?i=tt3896198&apikey=2088fa00")
    suspend fun getD() : Response<MovieResponse>
    @GET("/")
    suspend fun getMovieList(@Query("s") search:String, @Query("page") page:String,@Query("apikey")api_key:String) : Response<SearchResponse>

}