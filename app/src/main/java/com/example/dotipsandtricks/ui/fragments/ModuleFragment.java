package com.example.dotipsandtricks.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Modules;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.module.ModuleAdapter;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModuleFragment extends Fragment {

    private ModuleAdapter mAdapter;
    private PostService mService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_module, container, false);

        RecyclerView mRecyclerView = root.findViewById(R.id.rvModules);
        mAdapter = new ModuleAdapter(this.getContext(), new ArrayList<Modules>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemViewCacheSize(20);

        mService = ApiUtils.getPostService();

        loadModules();

        setHasOptionsMenu(true);

        return root;

    }

    private void loadModules() {

        Call<List<Modules>> call = mService.getModules();

        call.enqueue(new Callback<List<Modules>>() {
            @Override
            public void onResponse(@NonNull Call<List<Modules>> call, @NonNull Response<List<Modules>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateModules(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Modules>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in Call REST");
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_types_module, menu);
        //getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuDamage) {
            loadModulesFilter("Damage");
        }else if (id == R.id.menuEscudo) {
            loadModulesFilter("Shield");
        }else if (id == R.id.menuHP) {
            loadModulesFilter("HP");
        }else if (id == R.id.menuEspecial) {
            loadModulesFilter("Special");
        }else if (id == R.id.menuAllModules) {
            loadModules();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadModulesFilter(String s) {

        Call<List<Modules>> call = mService.getModulesFilter(s);

        call.enqueue(new Callback<List<Modules>>() {
            @Override
            public void onResponse(@NonNull Call<List<Modules>> call, @NonNull Response<List<Modules>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateModules(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Modules>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in Call REST");
            }
        });
    }

}