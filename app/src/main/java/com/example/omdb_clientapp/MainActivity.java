package com.example.omdb_clientapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.omdb_clientapp.api.RetrofitClient;
import com.example.omdb_clientapp.model.OmdbJsonResponse;
import com.example.omdb_clientapp.model.SearchResult;
import com.example.omdb_clientapp.ui.MovieListFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigateTo(new MovieListFragment());
        makeAPICall();
    }

    private void makeAPICall() {

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        RetrofitClient.MovieApi movieApiClient = retrofitClient.getMovieApiClient();

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

    public void navigateTo(Fragment destination) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, destination);
        transaction.commit();
    }

    private void logv(String message) {
        Log.v("MainActivtyLog", message);
    }

}
