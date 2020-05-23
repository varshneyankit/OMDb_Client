package com.example.omdb_clientapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.omdb_clientapp.R;
import com.example.omdb_clientapp.api.RetrofitClient;
import com.example.omdb_clientapp.model.ResultMetadata;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataFragment extends Fragment {

    private String imdbID;

    public MovieDataFragment(String imdbID){
        this.imdbID = imdbID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_movie_data,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeMovieDataRequest(imdbID);
    }

    public void makeMovieDataRequest(String imdbID){

        RetrofitClient client = RetrofitClient.getInstance();
        RetrofitClient.MovieApi movieApiClient = client.getMovieApiClient();

        Call<ResultMetadata> resultMetadataCall = movieApiClient.fetchItemByImdbId(imdbID);
        resultMetadataCall.enqueue(new Callback<ResultMetadata>() {
            @Override
            public void onResponse(Call<ResultMetadata> call, Response<ResultMetadata> response) {
                ResultMetadata body = response.body();

                if (body != null) {
                    Log.d("MovieData",body.toString());
                }

            }

            @Override
            public void onFailure(Call<ResultMetadata> call, Throwable t) {

            }
        });
    }
}
