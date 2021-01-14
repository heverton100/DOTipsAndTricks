package com.example.dotipsandtricks.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Skilltree;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.adapters.SkilltreeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillTreeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PostService mService;
    private SkilltreeAdapter mAdapter;

    public View onCreateView(@NonNull  LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_skill_tree, container, false);

        mRecyclerView = root.findViewById(R.id.rvSkilltree);
        mAdapter = new SkilltreeAdapter(this.getContext(), new ArrayList<Skilltree>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemViewCacheSize(20);

        mService = ApiUtils.getPostService();

        loadSkills();

        setHasOptionsMenu(true);

        return root;
    }

    private void loadSkills() {

        Call<List<Skilltree>> call = mService.getSkilltree();

        call.enqueue(new Callback<List<Skilltree>>() {
            @Override
            public void onResponse(@NonNull Call<List<Skilltree>> call, @NonNull Response<List<Skilltree>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateSkilltree(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Skilltree>> call,@NonNull Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_types_skill, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void loadSkillsfiltro(String s) {

        Call<List<Skilltree>> call = mService.getSkilltreefiltro(s);

        call.enqueue(new Callback<List<Skilltree>>() {
            @Override
            public void onResponse(@NonNull Call<List<Skilltree>> call, @NonNull Response<List<Skilltree>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateSkilltree(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Skilltree>> call,@NonNull Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });

    }

}