package com.example.dotipsandtricks.ui.fragments;

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
import com.example.dotipsandtricks.model.CategoryItem;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.item.CategoryItemsAdapter;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryItemsFragment extends Fragment {

    private CategoryItemsAdapter mAdapter;
    private PostService mService;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category_items, container, false);

        RecyclerView mRecyclerView = root.findViewById(R.id.rvCategoriesItems);
        mAdapter = new CategoryItemsAdapter(this.getContext(), new ArrayList<CategoryItem>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mService = ApiUtils.getPostService();

        loadCategories();

        return root;
    }

    private void loadCategories() {

        Call<List<CategoryItem>> call = mService.getCategories();

        call.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<CategoryItem>> call, @NonNull Response<List<CategoryItem>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateCategories(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CategoryItem>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in call REST");
            }
        });
    }

}