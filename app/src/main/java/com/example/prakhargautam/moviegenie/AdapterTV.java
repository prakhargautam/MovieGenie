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
 * Created by Sarvesh on 3/26/2016.
 */
public class AdapterTV extends ArrayAdapter<TV> {
    Context context;
    ArrayList<TV> batches;
    public AdapterTV(Context context, ArrayList<TV> objects) {
        super(context, 0, objects);
        this.context = context;
        batches = objects;
    }

    public static class ViewHolder{
        TextView title;
        TextView language;
        TextView overview;
        ImageView im;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context,R.layout.list_item_layout,null);
            TextView leftTextView = (TextView) convertView.findViewById(R.id.textView);
           /* TextView rightTextView = (TextView) convertView.findViewById(R.id.textView2);*/
            TextView downTextView = (TextView) convertView.findViewById(R.id.textView3);
            ImageView leftImageView=(ImageView) convertView.findViewById(R.id.imageView);
            ViewHolder vh = new ViewHolder();
            vh.title = leftTextView;
          /*  vh.language = rightTextView;*/
            vh.overview=downTextView;
            vh.im=leftImageView;
            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder)convertView.getTag();
        TV j = batches.get(position);
//ArrayList<TV> p=j.getResults();
        vh.title.setText(j.getOriginal_name());
        vh.overview.setText(j.getVote_average());
       /* vh.language.setText(j.getOriginal_language() + "");*/
        Picasso.with(context).load("http://image.tmdb.org/t/p/original"+j.getPoster_path()).fit().into(vh.im);

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
