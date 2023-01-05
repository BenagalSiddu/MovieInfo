package com.example.movieinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<MovieDetails> movieDetailsList;
    MovieDetails movieDetails;
    ImageView img_search;
    EditText edit_search;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_search = findViewById(R.id.img_search);
        edit_search = findViewById(R.id.edit_search);
        getSupportActionBar().hide();

        // default movie list
        getMovies("harry");

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to get searched movie list
                getMovies(edit_search.getText().toString());
            }
        });


        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
    }

    private void getMovies(String movieName) {

        Call<String> call1 = Api.getClient().getMovies(movieName, "a0783fa9");
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(response.body());
                    if (jsonObj != null) {
                        progressDoalog.dismiss();
                        String search = jsonObj.getString("Search");
                        JSONArray jsonArray = new JSONArray(search);
                        movieDetailsList = new ArrayList<>();
                        for (int n = 0; n < jsonArray.length(); n++) {
                            JSONObject object = jsonArray.getJSONObject(n);
                            movieDetails = new MovieDetails(object.getString("Title"),
                                    object.getString("Year"),
                                    object.getString("imdbID"),
                                    object.getString("Type"),
                                    object.getString("Poster"));
                            movieDetailsList.add(movieDetails);
                        }
                        GridView gridview = (GridView) findViewById(R.id.gridview);
                        gridview.setAdapter(new GridviewAdapter(MainActivity.this, movieDetailsList));
                    } else {
                        //progressDoalog.show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ERROR..." + t.getMessage());
                progressDoalog.dismiss();
            }
        });
    }
}