package com.example.prakhargautam.moviegenie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 3/26/2016.
 */
public interface ApiInterfaceforDiscover {
//    http://api.themoviedb.org/3/discover/movie?api_key=4cb5dafa2b58b941dff2c731fb26b8c7
//    &sort_by=popularity.desc
//    &primary_release_year=2014
    @GET("movie")
    Call<MovieList> discoverMovies(@Query("api_key")String key,@Query("sort_by")String order,@Query("primary_release_year") int year);


}
