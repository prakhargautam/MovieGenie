package com.example.prakhargautam.moviegenie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prakhargautam.moviegenie.Network.ApiClientforDiscover;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Discover extends Activity {
    ListView lv;
    MovieListAdapter adapterlv;
    ArrayList<Movie> al;
    private static String yr;
   private static String pop;
    ArrayAdapter<CharSequence> adapter,adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        yr="2015";
        pop="popularity.desc";



        final Spinner spinner = (Spinner) findViewById(R.id.poularity);
// Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.Year, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                yr = parent.getItemAtPosition(position).toString();
                if(pop.equals("Popularity Ascending"))
                {
                    pop="popularity.asc";
                }
                else
                {
                    pop="popularity.desc";
                }

                Call<MovieList> call= ApiClientforDiscover.getInterface().discoverMovies("4cb5dafa2b58b941dff2c731fb26b8c7"
                        ,pop,Integer.parseInt(yr));
                call.enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        if (response.isSuccessful()) {
                            MovieList mv=response.body();
                            al=mv.getResults();
                            adapterlv=new MovieListAdapter(Discover.this,al);
                            lv.setAdapter(adapterlv);

                        } else {
                            Toast.makeText(Discover.this,
                                    response.message() + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {
                        Toast.makeText(Discover.this,
                                "On failure", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }) ;











        Spinner spinner1 = (Spinner) findViewById(R.id.years);
// Create an ArrayAdapter using the string array and a default spinner layout
        adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Popularity, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pop=parent.getItemAtPosition(position).toString();
                Call<MovieList> call = ApiClientforDiscover.getInterface().discoverMovies("4cb5dafa2b58b941dff2c731fb26b8c7"
                        , pop, Integer.parseInt(yr));
                call.enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        if (response.isSuccessful()) {
                            MovieList mv = response.body();
                            al = mv.getResults();
                            adapterlv = new MovieListAdapter(Discover.this, al);
                            lv.setAdapter(adapterlv);

                        } else {
                            Toast.makeText(Discover.this,
                                    response.message() + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {
                        Toast.makeText(Discover.this,
                                "On failure", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


                lv = (ListView) findViewById(R.id.lv);
        Call<MovieList> call=ApiClientforDiscover.getInterface().discoverMovies("4cb5dafa2b58b941dff2c731fb26b8c7"
                ,pop,Integer.parseInt(yr));
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    MovieList mv = response.body();
                    al = mv.getResults();
                    adapterlv = new MovieListAdapter(Discover.this, al);
                    lv.setAdapter(adapterlv);
//                    Movie movie=al.get(1);

                    //work to be done   put to listview
                    // + image retreive thing
                    // +menus


                } else {
                    Toast.makeText(Discover.this,
                            response.message() + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(Discover.this,
                        "On failure", Toast.LENGTH_LONG).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent();
                i.putExtra("category","movie");
                i.putExtra("id",al.get(position).getId());
                i.setClass(Discover.this,DetailMovie.class);
                startActivity(i);
            }
        });
    }

}
