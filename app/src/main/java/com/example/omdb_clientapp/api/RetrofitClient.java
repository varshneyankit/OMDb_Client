package com.example.omdb_clientapp.api;

import com.example.omdb_clientapp.model.OmdbJsonResponse;
import com.example.omdb_clientapp.model.ResultMetadata;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitClient {

    private static final String API_KEY = "8195ca5d";
    private static RetrofitClient client = null;
    private Retrofit retrofit;

    private RetrofitClient() {
        this.retrofit = new Retrofit.Builder().baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {
        if (client == null) {
            client = new RetrofitClient();
        }
        return client;
    }

    public MovieApi getMovieApiClient() {
        MovieApi movieApi = retrofit.create(MovieApi.class);
        return movieApi;
    }

    public interface MovieApi {

        default Call<OmdbJsonResponse> searchItemByName(String query) {
            return searchItemByName(query, RetrofitClient.API_KEY);
        }

        default Call<ResultMetadata> fetchItemByImdbId(String query) {
            return fetchItemByImdbId(query, RetrofitClient.API_KEY);
        }

        @GET("/")
        Call<OmdbJsonResponse> searchItemByName(@Query("s") String name, @Query("apikey") String apikey);

        @GET("/")
        Call<ResultMetadata> fetchItemByImdbId(@Query("i") String imdbId, @Query("apikey") String apikey);
    }

}
