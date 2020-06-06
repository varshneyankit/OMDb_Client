package com.example.omdb_clientapp.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omdb_clientapp.MovieAdapter;
import com.example.omdb_clientapp.R;
import com.example.omdb_clientapp.api.RetrofitClient;
import com.example.omdb_clientapp.model.OmdbJsonResponse;
import com.example.omdb_clientapp.model.SearchResult;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends Fragment {
    MovieAdapter upAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText searchBox = view.findViewById(R.id.fragment_movie_list_search);
        ImageView button = view.findViewById(R.id.fragment_movie_list_search_button);
        button.setOnClickListener(v -> {
            Editable text = searchBox.getText();
            if (text != null && !TextUtils.isEmpty(text)) {
                makeApiSearchRequest(text.toString());
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.fragment_movie_list_recycler_view);
        upAdapter = new MovieAdapter(new ArrayList<>());
        recyclerView.setAdapter(upAdapter);


    }

    private void makeApiSearchRequest(String query) {

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        RetrofitClient.MovieApi movieApiClient = retrofitClient.getMovieApiClient();

        Call<OmdbJsonResponse> call = movieApiClient.searchItemByName(query);
        call.enqueue(new Callback<OmdbJsonResponse>() {
            @Override
            public void onResponse(Call<OmdbJsonResponse> call, Response<OmdbJsonResponse> response) {
                OmdbJsonResponse jsonResult = response.body();

                if (jsonResult != null && jsonResult.getResults() != null) {
                    updateAdapterData(jsonResult.getResults());
//                    for (SearchResult r : jsonResult.getResults()) {
//                        Log.d("Name ", r.getTitle());
//                        Log.d("Year ", r.getYear());
//                        Log.d("IMDB ", r.getImdbID());
//                        Log.d("Type", r.getType());
//                        Log.d("Poster ", r.getPoster());
//                    }
                }

            }


            @Override
            public void onFailure(Call<OmdbJsonResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private void updateAdapterData(List<SearchResult> results) {
        upAdapter.setResults(results);
        upAdapter.notifyDataSetChanged();
    }
}
