package com.example.movieinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GridviewAdapter extends BaseAdapter {

    private Context mContext;
    List<MovieDetails> movieDetailsList;

    public GridviewAdapter(Context c, List<MovieDetails> movieDetailsList1) {
        mContext = c;
        movieDetailsList = movieDetailsList1;
    }


    @Override
    public int getCount() {
        return movieDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_gridview, parent, false);
        ImageView img_movie = convertView.findViewById(R.id.img_movie);
        Picasso.with(mContext).load(movieDetailsList.get(position).getPoster()).into(img_movie);
        TextView tv_movieName = convertView.findViewById(R.id.tv_movieName);
        tv_movieName.setText(movieDetailsList.get(position).getTitle());
        TextView tv_movieYear = convertView.findViewById(R.id.tv_movieYear);
        tv_movieYear.setText(movieDetailsList.get(position).getYear());

        img_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("imdbID", movieDetailsList.get(position).getImdbID());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


}
