package com.example.omdb_clientapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.omdb_clientapp.R;
import com.example.omdb_clientapp.api.RetrofitClient;
import com.example.omdb_clientapp.model.ResultMetadata;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataFragment extends Fragment {

    private String imdbID;

    public MovieDataFragment(String imdbID) {
        this.imdbID = imdbID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeMovieDataRequest(imdbID);
    }

    private void makeMovieDataRequest(String imdbID) {

        RetrofitClient client = RetrofitClient.getInstance();
        RetrofitClient.MovieApi movieApiClient = client.getMovieApiClient();

        Call<ResultMetadata> resultMetadataCall = movieApiClient.fetchItemByImdbId(imdbID);
        resultMetadataCall.enqueue(new Callback<ResultMetadata>() {
            @Override
            public void onResponse(Call<ResultMetadata> call, Response<ResultMetadata> response) {
                ResultMetadata body = response.body();

                if (body != null) {
                    Log.d("MovieData", body.toString());
                    setMetaData(body);
                }
            }

            @Override
            public void onFailure(Call<ResultMetadata> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setMetaData(ResultMetadata body) {
        ImageView poster = getView().findViewById(R.id.fragment_movie_data_poster);
        TextView title = getView().findViewById(R.id.fragment_movie_data_title);
        TextView year = getView().findViewById(R.id.fragment_movie_data_year);
        TextView rated = getView().findViewById(R.id.fragment_movie_data_rated);
        TextView genre = getView().findViewById(R.id.fragment_movie_data_genre);
        TextView runtime = getView().findViewById(R.id.fragment_movie_data_runtime);
        TextView imdbRatings = getView().findViewById(R.id.fragment_movie_data_imdbRating);
        TextView plot = getView().findViewById(R.id.fragment_movie_data_plot);
        TextView directors = getView().findViewById(R.id.fragment_movie_data_directors);
        TextView cast = getView().findViewById(R.id.fragment_movie_data_cast);

        Picasso.get().load(body.getPoster()).into(poster);
        title.setText(body.getTitle());
        year.setText(body.getYear());
        rated.setText(body.getRated());
        genre.setText(body.getGenre());
        runtime.setText(body.getRuntime());
        imdbRatings.setText(body.getImdbRating());
        plot.setText(body.getPlot());
        directors.setText(body.getDirector());
        cast.setText(body.getCast());

    }
}
