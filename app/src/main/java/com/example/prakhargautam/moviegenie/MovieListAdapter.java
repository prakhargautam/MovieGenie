package com.example.prakhargautam.moviegenie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prakhargautam on 26/03/16.
 */
public class MovieListAdapter extends ArrayAdapter<Movie> {

    Context context;
    ArrayList<Movie> movies;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
        this.context=context;
        this.movies=movies;
    }

    public class ViewHolder{
        ImageView poster;
        TextView title;
        TextView tv_title;
        TextView overview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context,R.layout.movie_list_layout,null);
            ViewHolder vh= new ViewHolder();
            vh.poster=(ImageView) convertView.findViewById(R.id.poster);
            vh.title= (TextView) convertView.findViewById(R.id.title);
            vh.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
            vh.overview=(TextView) convertView.findViewById(R.id.overview);
            convertView.setTag(vh);
        }
        ViewHolder vh= (ViewHolder) convertView.getTag();
        vh.title.setText(movies.get(position).getName());
        vh.tv_title.setText(movies.get(position).getOriginal_name());
        vh.overview.setText(movies.get(position).getOverview());
        Picasso.with(context).
                load("https://image.tmdb.org/t/p/original"+movies.get(position).getPoster_path()+"?size=%20\"w100\"%20\"h150\"").
                fit().
                into(vh.poster);
        return convertView;
    }
}
