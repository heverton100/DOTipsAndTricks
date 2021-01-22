package com.example.dotipsandtricks.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Ships;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.ship.ShipAdapter;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipFragment extends Fragment{

    private ShipAdapter mAdapter;
    private PostService mService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ship, container, false);

        RecyclerView mRecyclerView = root.findViewById(R.id.rvNaves);
        mAdapter = new ShipAdapter(this.getContext(), new ArrayList<Ships>(0));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mService = ApiUtils.getPostService();

        loadNaves();

        return root;
    }

    private void loadNaves() {

        Call<List<Ships>> call = mService.getShips();

        call.enqueue(new Callback<List<Ships>>() {
            @Override
            public void onResponse(@NonNull Call<List<Ships>> call, @NonNull Response<List<Ships>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateNaves(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Ships>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in Call REST");
            }
        });
    }

}