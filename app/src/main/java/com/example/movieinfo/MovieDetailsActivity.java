package com.example.movieinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView img_movie_detail;
    TextView tv_movie_name, tv_rating_text, tv_timing_text,
            tv_movie_sub_name, tv_plot, tv_dire_text, tv_writer_text,
            tv_actors_text, tv_genre, tv_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        String imdbID = getIntent().getStringExtra("imdbID");
        getSupportActionBar().hide();
        img_movie_detail = findViewById(R.id.img_movie_detail);
        tv_movie_name = findViewById(R.id.tv_movie_name);
        tv_rating_text = findViewById(R.id.tv_rating_text);
        tv_timing_text = findViewById(R.id.tv_timing_text);
        tv_movie_sub_name = findViewById(R.id.tv_movie_sub_name);
        tv_plot = findViewById(R.id.tv_plot);
        tv_dire_text = findViewById(R.id.tv_dire_text);
        tv_writer_text = findViewById(R.id.tv_writer_text);
        tv_actors_text = findViewById(R.id.tv_actors_text);
        tv_genre = findViewById(R.id.tv_genre);
        tv_type = findViewById(R.id.tv_type);

        Call<String> call1 = Api.getClient().getMoviesDetails(imdbID, "a0783fa9");
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(response.body());
                    tv_movie_name.setText(jsonObj.getString("Title"));
                    tv_rating_text.setText(jsonObj.getString("imdbRating"));
                    String genre = jsonObj.getString("Genre");
                    String str[] = genre.split(",");
                    tv_genre.setText(str[0]);
                    tv_type.setText(jsonObj.getString("Type"));
                    tv_timing_text.setText(jsonObj.getString("Runtime"));
                    tv_movie_sub_name.setText(jsonObj.getString("Released"));
                    tv_plot.setText(jsonObj.getString("Plot"));
                    tv_dire_text.setText(jsonObj.getString("Director"));
                    tv_writer_text.setText(jsonObj.getString("Writer"));
                    tv_actors_text.setText(jsonObj.getString("Actors"));

                    Picasso.with(MovieDetailsActivity.this)
                            .load(jsonObj.getString("Poster"))
                            .into(img_movie_detail);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR..." + t.getMessage());
            }
        });

    }
}