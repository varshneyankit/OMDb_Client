package com.example.omdb_clientapp.api;

import com.example.omdb_clientapp.SearchResult;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitClient {

    public static final String API_KEY = "8195ca5d";
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

    public Retrofit getTvApiClient() {
        retrofit.create(TVApi.class);
        return retrofit;
    }

    public interface MovieApi {

        default Call<?/*Ambigious Generic*/> searchMoviesByTitle(String query) {
            return performQuery(query, RetrofitClient.API_KEY);
        }

        @GET("/")
        Call<ResponseBody> performQuery(@Query("s") String name, @Query("apikey") String apikey);

    }

    public interface TVApi {

        public abstract Call<List<ResponseBody>> searchTVByTitle();

    }


}
