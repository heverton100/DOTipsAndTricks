package com.example.dotipsandtricks.ui.items.category;

import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryItemsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CategoryItemsAdapter mAdapter;
    private PostService mService;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category_items, container, false);

        mRecyclerView = root.findViewById(R.id.rvCategoriasItens);
        mAdapter = new CategoryItemsAdapter(this.getContext(), new ArrayList<CategoryItem>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mService = ApiUtils.getPostService();

        loadCategorias();

        return root;
    }

    private void loadCategorias() {

        Call<List<CategoryItem>> call = mService.getCategorias();

        call.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateCategorias(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });
    }

}