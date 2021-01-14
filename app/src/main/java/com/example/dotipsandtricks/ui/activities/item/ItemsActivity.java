package com.example.dotipsandtricks.ui.activities.item;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Items;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.item.ItemsAdapter;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PostService mService;
    private ItemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        mRecyclerView = findViewById(R.id.rvItens);
        mAdapter = new ItemsAdapter(this, new ArrayList<Items>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mService = ApiUtils.getPostService();

        Intent i=this.getIntent();
        Integer title = i.getIntExtra("IDCATEGORIA",0);
        String x = i.getStringExtra("NOMECATEGORIA");

        getSupportActionBar().setTitle(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadItens(title);
    }


    private void loadItens(Integer i) {

        Call<List<Items>> call = mService.getItens(i);

        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(@NonNull Call<List<Items>> call, @NonNull Response<List<Items>> response) {

                if(response.isSuccessful())
                {
                    mAdapter.updateItens(response.body());
                    Log.d("MainActivity", "Retornou com sucesso");
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Items>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:

                onBackPressed();

                break;
        }
        return true;
    }

}