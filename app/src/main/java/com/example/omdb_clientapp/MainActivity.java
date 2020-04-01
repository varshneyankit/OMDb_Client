package com.example.omdb_clientapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.omdb_clientapp.api.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        RetrofitClient.MovieApi movieApiClient = retrofitClient.getMovieApiClient();
//        Retrofit tvApiClient = retrofitClient.getTvApiClient();


        Call<ResponseBody> call = (Call<ResponseBody>) movieApiClient.searchMoviesByTitle("Batman");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                logv(response.headers().toString());
                try {
                    assert response.body() != null;
                    logv(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logv(call.request().url().toString().concat("bodyyyyyyewe"));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
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
