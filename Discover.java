package com.example.prakhargautam.moviegenie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Discover extends Activity {
    private String entertain;
    ListView lv;
    //MovieListAdapter adapterlv;
    private static String yr;
   private static String pop;
    ArrayAdapter<CharSequence> adapter,adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        Intent intent=getIntent();
        if(intent.getStringExtra("entertain")==null)
        {
            entertain=new String("movie");
        }
        else
        {
            entertain=intent.getStringExtra("entertain");
        }
        yr=new String("2015");
        pop=new String("popularity.desc");



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
                if(entertain.equals("movie"))
                {
                    movieDisplay();
                }
                else
                {
                    tvDisplay();
                }
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
                if(entertain.equals("movie"))
                {
                    movieDisplay();
                }
                else
                {
                    tvDisplay();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


                lv = (ListView) findViewById(R.id.lv);
        if(entertain.equals("movie"))
        {
            movieDisplay();
        }
        else
        {
            tvDisplay();
        }
    }
    public void movieDisplay()
    {
        Call<MovieList> call=ApiClientforDiscover.getInterface().discoverMovies("4cb5dafa2b58b941dff2c731fb26b8c7"
                ,pop,Integer.parseInt(yr));
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    MovieList mv = response.body();
                    ArrayList<Movie> al = mv.getResults();
                    MovieListAdapter adapterlv = new MovieListAdapter(Discover.this, al);
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
    }
    public void tvDisplay()
    {
        Call<TVList> call=ApiClientforDiscover.getInterface().discoverTV("4cb5dafa2b58b941dff2c731fb26b8c7"
                , pop, Integer.parseInt(yr));
        call.enqueue(new Callback<TVList>() {
            @Override
            public void onResponse(Call<TVList> call, Response<TVList> response) {
                if (response.isSuccessful()) {
                    TVList mv = response.body();
                    ArrayList<TV> al = mv.getResults();
                    TVListadapter adapterlv = new TVListadapter(Discover.this, al);
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
            public void onFailure(Call<TVList> call, Throwable t) {
                Toast.makeText(Discover.this,
                        "On failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resource,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.movie)
        {
            if(!entertain.equals("movie"))
            {
                Intent intent=new Intent();
                intent.setClass(Discover.this,Discover.class);
                intent.putExtra("entertain", "movie");
                startActivity(intent);
            }

        }
        else if(item.getItemId()==R.id.tv)
        {
            if(entertain.equals("movie"))
            {
                Intent intent=new Intent();
                intent.setClass(Discover.this,Discover.class);
                intent.putExtra("entertain","tv");
                startActivity(intent);
            }
        }
        return true;
    }
}
