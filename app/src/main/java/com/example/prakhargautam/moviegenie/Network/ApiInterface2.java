package com.example.prakhargautam.moviegenie.Network;

import com.example.prakhargautam.moviegenie.TVList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by prakhargautam on 27/03/16.
 */
public interface ApiInterface2 {
    @GET("tv/top_rated")
    Call<TVList> getUsers(@Query("api_key") String api_key);
    @GET("tv/popular")
    Call<TVList> getpopular(@Query("api_key") String api_key);
    @GET("tv/on_the_air")
    Call<TVList> getup(@Query("api_key") String api_key);
}
