package com.example.prakhargautam.moviegenie;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.app.Fragment;
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

public class TopRatedTVFragment extends Fragment {
    AdapterTV adapter;
    ArrayList<TV> m;
    int req=1;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TopRatedTVFragment newInstance() {
        TopRatedTVFragment fragment = new TopRatedTVFragment();
        return fragment;
    }
    public TopRatedTVFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_top_rated_fragment, container, false);

        Call<TVList> usercall= ApiClient.getInterface2().getUsers("4cb5dafa2b58b941dff2c731fb26b8c7");

        usercall.enqueue(new Callback<TVList>() {
            @Override
            public void onResponse(Call<TVList> call, Response<TVList> response) {
                if(response.isSuccessful()) {
                    TVList p = response.body();
                    m=p.getResults();
                    if(getActivity()!=null) {
                    /*Toast.makeText(getActivity().getBaseContext(),m.get(1).getPoster_path()+"",Toast.LENGTH_LONG).show();*/
                        adapter = new AdapterTV(getActivity(), m);
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
            public void onFailure(Call<TVList> call, Throwable t) {
                Toast.makeText(getActivity(),"no",Toast.LENGTH_LONG).show();
            }
        });
        GridView sv=(GridView) rootView.findViewById(R.id.list_top);
        sv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i=new Intent();
                i.putExtra("id", m.get(position).getId());
                i.putExtra("category","tv");
                i.setClass(getActivity(),DetailMovie.class);
                startActivityForResult(i, req);
            }
        });
//        ListView lv=(ListView) rootView.findViewById(R.id.list_top);
        return rootView;
    }
}
