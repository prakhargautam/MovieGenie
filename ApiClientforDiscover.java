package com.example.prakhargautam.moviegenie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 3/26/2016.
 */
public class ApiClientforDiscover {
    private static ApiInterfaceforDiscover mService;
    public static ApiInterfaceforDiscover getInterface()
    {
        if(mService==null)
        {
            Retrofit retrofit=new Retrofit.Builder().baseUrl("http://api.themoviedb.org/3/discover/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService=retrofit.create(ApiInterfaceforDiscover.class);
        }
        return mService;
    }
}
