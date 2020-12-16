package com.example.dotipsandtricks.ui.module;

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
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModuleFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ModuleAdapter mAdapter;
    private PostService mService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_module, container, false);

        mRecyclerView = root.findViewById(R.id.rvModulos);
        mAdapter = new ModuleAdapter(this.getContext(), new ArrayList<Modules>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemViewCacheSize(20);

        mService = ApiUtils.getPostService();

        loadModulos();

        setHasOptionsMenu(true);

        return root;

    }

    private void loadModulos() {

        Call<List<Modules>> call = mService.getModulos();

        call.enqueue(new Callback<List<Modules>>() {
            @Override
            public void onResponse(Call<List<Modules>> call, Response<List<Modules>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateModulos(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Modules>> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_types_module, menu);
        //getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuDano) {
            loadModulosFiltro("Damage");
        }else if (id == R.id.menuEscudo) {
            loadModulosFiltro("Shield");
        }else if (id == R.id.menuHP) {
            loadModulosFiltro("HP");
        }else if (id == R.id.menuEspecial) {
            loadModulosFiltro("Special");
        }else if (id == R.id.menuTodosModulos) {
            loadModulos();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadModulosFiltro(String s) {

        Call<List<Modules>> call = mService.getModulosFiltro(s);

        call.enqueue(new Callback<List<Modules>>() {
            @Override
            public void onResponse(Call<List<Modules>> call, Response<List<Modules>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateModulos(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Modules>> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });
    }

}