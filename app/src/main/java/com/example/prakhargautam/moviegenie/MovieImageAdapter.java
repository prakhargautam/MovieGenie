package com.example.prakhargautam.moviegenie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by prakhargautam on 27/03/16.
 */
public class MovieImageAdapter extends ArrayAdapter<Movie> {
    Context context;
    ArrayList<Movie> movies;
    public MovieImageAdapter(Context context, ArrayList<Movie> objects) {
        super(context, 0, objects);
        this.context = context;
        movies = objects;
    }

    public static class ViewHolder{
        ImageView im;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context, R.layout.list_image_layout,null);

            ImageView leftImageView=(ImageView) convertView.findViewById(R.id.imageView);
            ViewHolder vh = new ViewHolder();
            vh.im=leftImageView;
            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder)convertView.getTag();
        Picasso.with(context).load("http://image.tmdb.org/t/p/original"+movies.get(position).
                getPoster_path()).
                fit().
                into(vh.im);

        return convertView;
    }

}
