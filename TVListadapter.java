package com.example.prakhargautam.moviegenie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 3/28/2016.
 */
public class TVListadapter extends ArrayAdapter<TV> {
    Context context;
    ArrayList<TV> tvs;


    public TVListadapter(Context context, ArrayList<TV> tvs) {
        super(context,0, tvs);
        this.context = context;
        this.tvs = tvs;
    }

    public class ViewHolder{
        ImageView poster;
        TextView title;
        TextView overview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context,R.layout.movie_list_layout,null);
            ViewHolder vh= new ViewHolder();
            vh.poster=(ImageView) convertView.findViewById(R.id.poster);
            vh.title=(TextView) convertView.findViewById(R.id.title);
            vh.overview=(TextView) convertView.findViewById(R.id.overview);
            convertView.setTag(vh);
        }
        ViewHolder vh= (ViewHolder) convertView.getTag();
        Picasso.with(context).
                load("https://image.tmdb.org/t/p/original"+tvs.get(position).getPoster_path()).
                fit().
                into(vh.poster);
        vh.title.setText(tvs.get(position).getOriginal_name());
        vh.overview.setText(tvs.get(position).getOverview());
        return convertView;
    }
}
