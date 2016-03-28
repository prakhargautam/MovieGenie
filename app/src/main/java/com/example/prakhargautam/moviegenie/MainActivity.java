package com.example.prakhargautam.moviegenie;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.prakhargautam.moviegenie.Network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String API_KEY="4cb5dafa2b58b941dff2c731fb26b8c7";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    ImageButton search;
    EditText searchText;
    Button discover;
    Button movie;
    Button tv;

    GridView movieGridView;
    GridView tvGridView;

    ArrayList<Movie> movies;
    MovieList movieList;

    TVList tvList;
    ArrayList<TV> tvs;

    MovieImageAdapter movieImageAdapter;
    TVImageAdapter tvImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView imageView= (ImageView) findViewById(R.id.movie_poster);


//        String baseUrl="https://api.themoviedb.org/3/movie/550/images/";

        movieGridView= (GridView) findViewById(R.id.movieGridView);
        tvGridView= (GridView) findViewById(R.id.tvGridView);

        search= (ImageButton) findViewById(R.id.search_button);
        searchText= (EditText) findViewById(R.id.search_text);

        searchText.setText("");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((searchText.getText() != null)&&!searchText.getText().toString().equals("")) {
                    Intent i = new Intent();
                    i.putExtra("name", searchText.getText().toString());
                    i.setClass(MainActivity.this, SearchActivity.class);
                    startActivity(i);
                }
            }
        });

        discover=(Button) findViewById(R.id.discover);
        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, Discover.class);
                startActivity(i);
            }
        });
        movie= (Button) findViewById(R.id.movies);
        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, MoviesActivity.class);
                startActivity(i);
            }
        });

        tv=(Button) findViewById(R.id.tvshows);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent();
                i.setClass(MainActivity.this, TVShowActivity.class);
                startActivity(i);
            }
        });

        movies= new ArrayList<>();

        Call<MovieList> movieListCall= ApiClient.getInterface().getNowPlaying(API_KEY);
        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieList = response.body();
                for (int i = 0; i < 6; i++) {
                    movies.add(i, movieList.getResults().get(i));
                }
                movieImageAdapter = new MovieImageAdapter(MainActivity.this, movies);
                movieGridView.setAdapter(movieImageAdapter);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
        tvs= new ArrayList<>();

        Call<TVList> tvListCall=ApiClient.getInterface().getOnTheAir(API_KEY);
        tvListCall.enqueue(new Callback<TVList>() {
            @Override
            public void onResponse(Call<TVList> call, Response<TVList> response) {
                tvList= response.body();
                for(int i=0;i<6;i++){
                    tvs.add(i,tvList.getResults().get(i));
                }
                tvImageAdapter= new TVImageAdapter(MainActivity.this,tvs);
                tvGridView.setAdapter(tvImageAdapter);
            }

            @Override
            public void onFailure(Call<TVList> call, Throwable t) {

            }
        });

        movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent();
                i.putExtra("category","movie");
                i.putExtra("id",movies.get(position).getId());
                i.setClass(MainActivity.this,DetailMovie.class);
                startActivity(i);
            }
        });

        tvGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent();
                i.putExtra("category","tv");
                i.putExtra("id",tvs.get(position).getId());
                i.setClass(MainActivity.this, DetailMovie.class);
                startActivity(i);
            }
        });





        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    public void onClick(View view){
//        int id= view.getId();
//        Intent i= new Intent();
//        if()
        Toast.makeText(MainActivity.this,"stuff",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sample, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
