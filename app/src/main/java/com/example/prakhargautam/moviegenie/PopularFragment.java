package com.example.prakhargautam.moviegenie;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.prakhargautam.moviegenie.Network.ApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sarvesh on 3/27/2016.
 */
public class PopularFragment extends Fragment {

    AdapterMovie adapter;
    ArrayList<Movie> m;
    int req=1;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
        return fragment;
    }
    public PopularFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_top_rated_fragment, container, false);
        Gson gson = new GsonBuilder().create();

        Call<MovieList> usercall= ApiClient.getInterface().getpopular("4cb5dafa2b58b941dff2c731fb26b8c7");

        usercall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response.isSuccessful()) {
                    MovieList p = response.body();
                    m=p.getResults();
                    if(getActivity()!=null) {
                  /*  Toast.makeText(getActivity().getBaseContext(), m.get(1).getPoster_path() + "", Toast.LENGTH_LONG).show();*/
                        adapter = new AdapterMovie(getActivity(), m);
                        GridView lv = (GridView) rootView.findViewById(R.id.list_top);
                        lv.setAdapter(adapter);
                    }


                }
                else
                {
                    Toast.makeText(getActivity(),"yes",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(getActivity(),"no",Toast.LENGTH_LONG).show();
            }
        });
        GridView sv=(GridView) rootView.findViewById(R.id.list_top);
        sv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i=new Intent();
                i.putExtra("id", m.get(position).getId());
                i.putExtra("category","movie");
                i.setClass(getActivity(),DetailMovie.class);
                startActivityForResult(i, req);
            }
        });
        return rootView;
    }
}
