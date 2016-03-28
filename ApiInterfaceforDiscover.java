package com.example.prakhargautam.moviegenie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 3/26/2016.
 */
public interface ApiInterfaceforDiscover {
//    http://api.themoviedb.org/3/discover/movie?api_key=4cb5dafa2b58b941dff2c731fb26b8c7
//    &sort_by=popularity.desc
//    &primary_release_year=2014
    @GET("discover/movie")
    Call<MovieList> discoverMovies(@Query("api_key")String key,@Query("sort_by")String order,@Query("primary_release_year") int year);

    @GET("discover/tv")
    Call<TVList> discoverTV(@Query("api_key")String key,@Query("sort_by")String order,@Query("primary_release_year") int year);

    //https://api.themoviedb.org/3/movie/550?api_key=###
    @GET("movie/{id}")
    Call<Movie> detail_movie(@Path("id")int id,@Query("api_key")String key);

    //fetching image
//    @GET("{id}")
//    Call<> image_fetch(@Path("id")int id);

    @GET("tv/{id}")
    Call<TV> detail_tv(@Path("id")int id,@Query("api_key")String key);
}
