package com.example.dotipsandtricks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.dotipsandtricks.model.Ships;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.activities.user.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private PostService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mService = ApiUtils.getPostService();

        loadAPI();

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLogin();
            }
        }, 2000);
    }

    private void mostrarLogin() {
        Intent intent = new Intent(SplashActivity.this,
                //MainActivity.class);
                LoginActivity.class);
        startActivityForResult(intent,1);
        finish();
    }

    private void loadAPI() {

        Call<List<Ships>> call = mService.getNaves();

        call.enqueue(new Callback<List<Ships>>() {
            @Override
            public void onResponse(@NonNull Call<List<Ships>> call, @NonNull Response<List<Ships>> response) {

                if(response.isSuccessful()) {
                    Log.d("MainActivity", "SUCCESS");
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Ships>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });
    }
}