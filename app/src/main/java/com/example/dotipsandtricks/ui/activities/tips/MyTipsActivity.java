package com.example.dotipsandtricks.ui.activities.tips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Tips;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.tips.MyTipsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTipsActivity extends AppCompatActivity {

    private PostService mService;
    private MyTipsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tips);

        getSupportActionBar().setTitle("My Tips");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView mRecyclerView = findViewById(R.id.rvMyTips);
        mAdapter = new MyTipsAdapter(this, new ArrayList<Tips>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);

        mService = ApiUtils.getPostService();

        Intent i=this.getIntent();
        Integer id = i.getIntExtra("ID_USER",0);

        returnTips(id);
    }

    public void returnTips(Integer id){

        Call<List<Tips>> call = mService.getMyTips(id,id);

        call.enqueue(new Callback<List<Tips>>() {
            @Override
            public void onResponse(@NonNull Call<List<Tips>> call, @NonNull Response<List<Tips>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateMyTips(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Tips>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in Call REST");
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}