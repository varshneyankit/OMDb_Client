package com.example.omdb_clientapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.omdb_clientapp.api.RetrofitClient;

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


        Call<ResponseBody> call = movieApiClient.searchMoviesByTitle("Batman");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                logv(response.headers().toString());
                logv(response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                logv(call.toString());
                t.printStackTrace();
            }
        });


    }

    public void logv(String message) {
        Log.v("MainActivtyLog", message);
    }
}
