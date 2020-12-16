package com.example.dotipsandtricks.ui.skilltree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Skilltree;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkilltreeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PostService mService;
    private SkilltreeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skilltree);

        getSupportActionBar().setTitle("Skill Tree");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.rvSkilltree);
        mAdapter = new SkilltreeAdapter(this, new ArrayList<Skilltree>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemViewCacheSize(20);

        mService = ApiUtils.getPostService();

        loadSkills();
    }

    private void loadSkills() {

        Call<List<Skilltree>> call = mService.getSkilltree();

        call.enqueue(new Callback<List<Skilltree>>() {
            @Override
            public void onResponse(Call<List<Skilltree>> call, Response<List<Skilltree>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateSkilltree(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Skilltree>> call, Throwable t) {
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
            case R.id.menuSkillDefensive:

                loadSkillsfiltro("Defensive");

                break;
            case R.id.menuSkillOffensive:

                loadSkillsfiltro("Offensive");

                break;
            case R.id.menuSkillResource:

                loadSkillsfiltro("Resource");

                break;
            case R.id.menuTodosSkill:

                loadSkills();

                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_types_skill, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadSkillsfiltro(String s) {

        Call<List<Skilltree>> call = mService.getSkilltreefiltro(s);

        call.enqueue(new Callback<List<Skilltree>>() {
            @Override
            public void onResponse(Call<List<Skilltree>> call, Response<List<Skilltree>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateSkilltree(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Skilltree>> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });

    }
}