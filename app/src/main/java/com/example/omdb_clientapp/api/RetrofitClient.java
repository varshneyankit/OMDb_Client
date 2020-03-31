package com.example.omdb_clientapp.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitClient {

    private Retrofit retrofit;
    private static RetrofitClient client = null;

    private RetrofitClient() {
        this.retrofit = new Retrofit.Builder().baseUrl("http://www.omdbapi.com").build();
    }

    public static RetrofitClient getInstance(){
        if (client == null) {
            client = new RetrofitClient();
        }
        return client;
    }

    public MovieApi getMovieApiClient(){
        MovieApi movieApi = retrofit.create(MovieApi.class);
        return movieApi;
    }

    public Retrofit getTvApiClient(){
        retrofit.create(TVApi.class);
        return retrofit;
    }

    public interface MovieApi {

        @GET("apikey=8195ca5d")
        Call<ResponseBody> searchMoviesByTitle(@Query("s") String name);
    }

    public interface TVApi{

        public abstract Call<List<ResponseBody>> searchTVByTitle();

    }



}
