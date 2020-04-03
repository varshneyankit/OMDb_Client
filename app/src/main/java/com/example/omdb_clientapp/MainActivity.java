package com.example.omdb_clientapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.omdb_clientapp.api.RetrofitClient;
import com.example.omdb_clientapp.model.OmdbJsonResponse;
import com.example.omdb_clientapp.model.SearchResult;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout fl = findViewById(R.id.fragment_container);




        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        RetrofitClient.MovieApi movieApiClient = retrofitClient.getMovieApiClient();
//        Retrofit tvApiClient = retrofitClient.getTvApiClient();


        Call<OmdbJsonResponse> call = movieApiClient.searchMoviesByTitle("Batman");
        call.enqueue(new Callback<OmdbJsonResponse>() {
            @Override
            public void onResponse(Call<OmdbJsonResponse> call, Response<OmdbJsonResponse> response) {
                OmdbJsonResponse Results = response.body();

                if (Results != null && Results.getResults() != null) {
                    for (SearchResult r : Results.getResults()) {
                        Log.d("Name ", r.getTitle());
                        Log.d("Year ", r.getYear());
                        Log.d("IMDB ", r.getImdbID());
                        Log.d("Type", r.getType());
                        Log.d("Poster ", r.getPoster());
                    }
                }
            }

            @Override
            public void onFailure(Call<OmdbJsonResponse> call, Throwable t) {
                logv(call.toString());
                logv(call.request().url().toString().concat("body127653"));
                t.printStackTrace();
            }
        });

    }

    public void logv(String message) {
        Log.v("MainActivtyLog", message);
    }
}
