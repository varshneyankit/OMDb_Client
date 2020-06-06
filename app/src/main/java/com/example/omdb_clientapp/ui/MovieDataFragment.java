package com.example.omdb_clientapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
        View parentView = getView();

        ImageView poster = parentView.findViewById(R.id.fragment_movie_data_poster);
        TextView title = parentView.findViewById(R.id.fragment_movie_data_title);
        TextView year = parentView.findViewById(R.id.fragment_movie_data_year);
        TextView rated = parentView.findViewById(R.id.fragment_movie_data_rated);
        TextView runtime = parentView.findViewById(R.id.fragment_movie_data_runtime);
        TextView imdbRatings = parentView.findViewById(R.id.fragment_movie_data_imdbRating);
        TextView plot = parentView.findViewById(R.id.fragment_movie_data_plot);
        TextView directors = parentView.findViewById(R.id.fragment_movie_data_directors);
        TextView cast = parentView.findViewById(R.id.fragment_movie_data_cast);

        Picasso.get().load(body.getPoster()).into(poster);
        title.setText(body.getTitle());
        year.setText(body.getYear());
        rated.setText(body.getRated());
        directors.setText(body.getDirector());

        directors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // TODO implement genre buttons
//        HorizontalScrollView scrollView = parentView.findViewById(R.id.fragment_movie_data_genre_hscroll_view);
        String[] genres = body.getGenre().split(", ");

//        for (String genre : genres) {
//            View inflate = getLayoutInflater().inflate(R.layout.item_genre_movie_data, null);
//            Button textButton = inflate.findViewById(R.id.movie_genre_text);
//            textButton.setText(genre);
//            scrollView.addView(inflate);
//        }

//        GridView gridView = parentView.findViewById(R.id.fragment_movie_data_genre_grid_view);
//        gridView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//
//        gridView.setAdapter(new ArrayAdapter<String>(this.getContext(), R.layout.item_genre_movie_data, genres) {
//            @NonNull
//            @Override
//            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                if (convertView == null) {
//                    convertView = getLayoutInflater().inflate(R.layout.item_genre_movie_data, parent, false);
//                }
//                Button textButton = convertView.findViewById(R.id.movie_genre_text);
//                textButton.setText(genres[position]);
//                return convertView;
//            }
//        });

        runtime.setText(body.getRuntime());
        imdbRatings.setText(body.getImdbRating() + " / 10");
        plot.setText(body.getPlot());
        cast.setText(body.getCast());

        plot.setOnClickListener(v -> {
            if (plot.getMaxLines() == Integer.MAX_VALUE) {
                plot.setMaxLines(4);
            } else
                plot.setMaxLines(Integer.MAX_VALUE);
        });

    }
}
