package com.example.prakhargautam.moviegenie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prakhargautam.moviegenie.Network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovie extends Activity {

    ImageView imageView;
    TextView Movietitle;
    TextView Rating;
    TextView detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent i= getIntent();
        int id=i.getIntExtra("id",0);
        String category=i.getStringExtra("category");
        imageView=(ImageView)findViewById(R.id.imageView);
        Movietitle=(TextView)findViewById(R.id.textView);
        Rating=(TextView)findViewById(R.id.textView2);
        detail=(TextView)findViewById(R.id.textView4);
        if(category.equals("movie"))
        {
            Call<Movie> call= ApiClient.getInterface().detail_movie(id,"4cb5dafa2b58b941dff2c731fb26b8c7");
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response.isSuccessful()) {
                        Movie movie=response.body();
                        Movietitle.setText(movie.getName());
                        Rating.setText("Rating: "+movie.getVote_average());
                        detail.setText(movie.getOverview());
                        Picasso.with(DetailMovie.this)
                                .load("https://image.tmdb.org/t/p/original/"+movie.getPoster_path())
                                .fit()
                                .into(imageView);
                    } else {
                        Toast.makeText(DetailMovie.this,
                                response.message() + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Toast.makeText(DetailMovie.this,
                            "On failure", Toast.LENGTH_LONG).show();
                }
            });
        }
        else if(category.equals("tv"))
        {
            Call<TV> call=ApiClient.getInterface().detail_tv(id,"4cb5dafa2b58b941dff2c731fb26b8c7");
            call.enqueue(new Callback<TV>() {
                @Override
                public void onResponse(Call<TV> call, Response<TV> response) {
                    if (response.isSuccessful()) {
                        TV tv=response.body();
                        Movietitle.setText(tv.getOriginal_name());
                        Rating.setText("Rating: "+tv.getVote_average());
                        detail.setText(tv.getOverview());
                        Picasso.with(DetailMovie.this)
                                .load("https://image.tmdb.org/t/p/original/"+tv.getPoster_path())
                                .fit()
                                .into(imageView);
                    } else {
                        Toast.makeText(DetailMovie.this,
                                response.message() + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<TV> call, Throwable t) {
                    Toast.makeText(DetailMovie.this,
                            "On failure", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
