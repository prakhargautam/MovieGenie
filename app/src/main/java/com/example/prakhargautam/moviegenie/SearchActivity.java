package com.example.prakhargautam.moviegenie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.prakhargautam.moviegenie.Network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends Activity {

    public static final String API_KEY="4cb5dafa2b58b941dff2c731fb26b8c7";

    ArrayList<Movie> movies;
    MovieListAdapter adapter;
    ListView movieListView;
    MovieList movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        movieListView= (ListView) findViewById(R.id.movieListView);

        Intent i=getIntent();
        String searchName= i.getStringExtra("name");
        final Call<MovieList> movieListCall= ApiClient.getInterface().getSearchResults(searchName,API_KEY);

        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieList = response.body();
                movies = movieList.getResults();
                adapter= new MovieListAdapter(SearchActivity.this,movies);
                movieListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
            }
        });

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(movies.get(position).getOriginal_name()==null){
                    //movie
                    Intent i= new Intent();
                    i.putExtra("category","movie");
                    i.putExtra("id",movies.get(position).getId());
                    i.setClass(SearchActivity.this, DetailMovie.class);
                    startActivity(i);
                }
                else{
                    //tv
                    Intent i= new Intent();
                    i.putExtra("category","tv");
                    i.putExtra("id", movies.get(position).getId());
                    i.setClass(SearchActivity.this, DetailMovie.class);
                    startActivity(i);
                }
            }
        });
    }

}
