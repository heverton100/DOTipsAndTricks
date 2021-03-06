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

    ShipModulesAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =  inflater.inflate(R.layout.fragment_ship_modules, container, false);

        RecyclerView mRecyclerView = root.findViewById(R.id.rvModulesShip);
        mAdapter = new ShipModulesAdapter(this.getContext(), new ArrayList<Modules>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);


        PostService mService = ApiUtils.getPostService();

        Intent i=this.getActivity().getIntent();
        Integer title = i.getIntExtra("ID_NAVE",0);

        Call<List<Modules>> call = mService.getShipModules(title);

        call.enqueue(new Callback<List<Modules>>() {
            @Override
            public void onResponse(@NonNull Call<List<Modules>> call, @NonNull Response<List<Modules>> response) {

                if(response.isSuccessful()) {

                    if (!response.body().get(0).getNameModule().equals("TESTE")) {
                        mAdapter.updateModules(response.body());
                    }

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

        return root;
    }
}