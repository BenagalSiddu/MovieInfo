package com.example.movieinfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/?")
    Call<String> getMovies(@Query("s") String mname, @Query("apikey") String key);

    @GET("/?")
    Call<String> getMoviesDetails(@Query("i") String imdbID, @Query("apikey") String key);

}
