package com.example.prakhargautam.moviegenie.Network;

import com.example.prakhargautam.moviegenie.Movie;
import com.example.prakhargautam.moviegenie.MovieList;
import com.example.prakhargautam.moviegenie.TV;
import com.example.prakhargautam.moviegenie.TVList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by prakhargautam on 26/03/16.
 */
public interface ApiInterface {

    @GET("movie/now_playing")
    Call<MovieList> getNowPlaying(@Query("api_key") String key);

    @GET("search/multi")
    Call<MovieList> getSearchResults(@Query("query") String name, @Query("api_key") String key);

    @GET("tv/on_the_air")
    Call<TVList> getOnTheAir(@Query("api_key") String key);

    @GET("discover/movie")
    Call<MovieList> discoverMovies(@Query("api_key")String key,@Query("sort_by")String order,@Query("primary_release_year") int year);

    //https://api.themoviedb.org/3/movie/550?api_key=###
    @GET("movie/{id}")
    Call<Movie> detail_movie(@Path("id")int id,@Query("api_key")String key);

    //fetching image
//    @GET("{id}")
//    Call<> image_fetch(@Path("id")int id);

    @GET("tv/{id}")
    Call<TV> detail_tv(@Path("id")int id,@Query("api_key")String key);

    @GET("movie/top_rated")
    Call<MovieList> getUsers(@Query("api_key") String api_key);
    @GET("movie/popular")
    Call<MovieList> getpopular(@Query("api_key") String api_key);
    @GET("movie/upcoming")
    Call<MovieList> getup(@Query("api_key") String api_key);

}
