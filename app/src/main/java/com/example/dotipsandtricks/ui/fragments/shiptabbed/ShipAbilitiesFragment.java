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
import com.example.dotipsandtricks.model.ShipAbilities;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.ship.ShipAbilityAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipAbilitiesFragment extends Fragment {

    private PostService mService;
    private RecyclerView mRecyclerView;
    ShipAbilityAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =  inflater.inflate(R.layout.fragment_ship_abilities, container, false);

        mRecyclerView = root.findViewById(R.id.rvShipAbilities);
        adapter = new ShipAbilityAdapter(this.getContext(), new ArrayList<ShipAbilities>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);


        mService = ApiUtils.getPostService();

        Intent i=this.getActivity().getIntent();
        Integer ship = i.getIntExtra("IDNAVE",0);

        Call<List<ShipAbilities>> call = mService.getAbilities(ship);

        call.enqueue(new Callback<List<ShipAbilities>>() {
            @Override
            public void onResponse(@NonNull Call<List<ShipAbilities>> call, @NonNull Response<List<ShipAbilities>> response) {

                if(response.isSuccessful()) {

                    if (response.body().get(0).getAbilityName().equals("TESTE")) {
                        //Toast.makeText(root.getContext(),"This ship does not have ability!",Toast.LENGTH_SHORT).show();
                    }else{
                        adapter.updateAbilities(response.body());
                    }

                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ShipAbilities>> call,@NonNull Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });

        return root;
    }
}