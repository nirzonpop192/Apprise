package com.faisal.dc.apprise

import com.faisal.dc.apprise.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//@GET("/resolve.json?url={url}&client_id={clientId}")
//Call<Track> getTrack(@Path("url") String url,@Path("clientId") String clientId);
//@GET("?i=tt3896198&apikey=2088fa00")
interface AppApi {
    @GET("/")
    suspend fun getMovie(@Query("i") imd_id:String, @Query("apikey")api_key:String) : Response<MovieResponse>
    @GET("/?i=tt3896198&apikey=2088fa00")
    suspend fun getD() : Response<MovieResponse>
}