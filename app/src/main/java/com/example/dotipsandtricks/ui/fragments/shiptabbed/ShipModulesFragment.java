package com.example.dotipsandtricks.ui.fragments.shiptabbed;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Modules;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.ship.ShipModulesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipModulesFragment extends Fragment {

    ShipModulesAdapter modulesShipAdapter;
    private PostService mService;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =  inflater.inflate(R.layout.fragment_ship_modules, container, false);

        mRecyclerView = root.findViewById(R.id.rvModulesShip);
        modulesShipAdapter = new ShipModulesAdapter(this.getContext(), new ArrayList<Modules>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(modulesShipAdapter);
        mRecyclerView.setHasFixedSize(true);


        mService = ApiUtils.getPostService();

        Intent i=this.getActivity().getIntent();
        Integer title = i.getIntExtra("IDNAVE",0);

        Call<List<Modules>> call = mService.getNaveModulos(title);

        call.enqueue(new Callback<List<Modules>>() {
            @Override
            public void onResponse(@NonNull Call<List<Modules>> call, @NonNull Response<List<Modules>> response) {

                if(response.isSuccessful()) {

                    if (response.body().get(0).getNomeModulo().equals("TESTE")) {
                        //Toast.makeText(root.getContext(),"This ship does not have modules!",Toast.LENGTH_SHORT).show();
                    }else{
                        modulesShipAdapter.updateModules(response.body());
                    }

                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Modules>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });

        return root;
    }
}