package com.example.dotipsandtricks.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.PointsPilot;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.LogDiskCostChartAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogDiskCostChartFragment extends Fragment {

    private PostService mService;
    private LogDiskCostChartAdapter mAdapter;

    public FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_log_disk_cost_chart, container, false);

        RecyclerView mRecyclerView = root.findViewById(R.id.rvResearchPoints);
        mAdapter = new LogDiskCostChartAdapter(this.getContext(), new ArrayList<PointsPilot>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemViewCacheSize(20);

        mService = ApiUtils.getPostService();

        loadPPs();

        fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTextDialog();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    fab.setVisibility(View.GONE);
                } else {
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });

        return root;
    }

    private void loadPPs() {

        Call<List<PointsPilot>> call = mService.getPP();

        call.enqueue(new Callback<List<PointsPilot>>() {
            @Override
            public void onResponse(@NonNull Call<List<PointsPilot>> call, @NonNull Response<List<PointsPilot>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updatePPs(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PointsPilot>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in Call REST");
            }
        });
    }

    private void showTextDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Enter the number of PPs you have:");

        final EditText input = new EditText(this.getContext());

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txtTest = input.getText().toString();
                loadPPsFilter(txtTest);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void loadPPsFilter(String i) {

        Call<List<PointsPilot>> call = mService.getPPfilter(i);

        call.enqueue(new Callback<List<PointsPilot>>() {
            @Override
            public void onResponse(@NonNull Call<List<PointsPilot>> call, @NonNull Response<List<PointsPilot>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updatePPs(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PointsPilot>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in Call REST");
            }
        });
    }

}