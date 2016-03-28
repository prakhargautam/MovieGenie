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
public class TVImageAdapter extends ArrayAdapter<TV> {

    Context context;
    ArrayList<TV> tvs;

    public TVImageAdapter(Context context, ArrayList<TV> tvs) {
        super(context, 0, tvs);
        this.context=context;
        this.tvs=tvs;
    }

    public class ViewHolder{
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context,R.layout.list_image_layout,null);
            ViewHolder vh= new ViewHolder();
            vh.imageView=(ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(vh);
        }
        ViewHolder vh= (ViewHolder) convertView.getTag();
        Picasso.with(context).
                load("https://image.tmdb.org/t/p/original"+tvs.get(position).getPoster_path()).
                fit().
                into(vh.imageView);
        return convertView;
    }
}
