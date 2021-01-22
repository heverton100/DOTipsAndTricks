package com.example.dotipsandtricks.ui.fragments;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Tips;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.activities.tips.MyTipsActivity;
import com.example.dotipsandtricks.ui.activities.tips.NewTipActivity;
import com.example.dotipsandtricks.ui.adapters.tips.TipsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipsFragment extends Fragment {

    private PostService mService;
    private TipsAdapter mAdapter;

    FloatingActionButton fabNewTip,fabMyTip;
    Boolean isFABOpen = false;

    ConstraintLayout ll1;
    LinearLayout ll2,ll3;
    TextView lbl1, lbl2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_tips, container, false);

        RecyclerView mRecyclerView = root.findViewById(R.id.rvTips);
        mAdapter = new TipsAdapter(this.getContext(), new ArrayList<Tips>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);

        mService = ApiUtils.getPostService();

        returnTips();

        ll1 = root.findViewById(R.id.clFabMenu);
        ll2 = root.findViewById(R.id.llFab2);
        ll3 = root.findViewById(R.id.llFab3);

        lbl1 = root.findViewById(R.id.lblFab1);
        lbl2 = root.findViewById(R.id.lblFab2);

        FloatingActionButton fab = root.findViewById(R.id.fabTip);
        fabNewTip = root.findViewById(R.id.fabNewTip);
        fabMyTip = root.findViewById(R.id.fabMyTips);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }

            }
        });

        fabNewTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(root.getContext(), NewTipActivity.class);
                root.getContext().startActivity(i);
            }
        });

        fabMyTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(root.getContext(), MyTipsActivity.class);
                i.putExtra("ID_USER", returnIDuser());
                root.getContext().startActivity(i);
            }
        });


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    ll1.setVisibility(View.GONE);
                    closeFABMenu();
                } else {
                    ll1.setVisibility(View.VISIBLE);
                }
            }
        });

        //setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onResume() {
        returnTips();
        closeFABMenu();
        super.onResume();
    }

    public Integer returnIDuser(){
        int id;
        SharedPreferences prefs = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        id = prefs.getInt("userid",0);
        return id;
    }

    public void returnTips(){

        Call<List<Tips>> call = mService.getTips(returnIDuser());

        call.enqueue(new Callback<List<Tips>>() {
            @Override
            public void onResponse(@NonNull Call<List<Tips>> call, @NonNull Response<List<Tips>> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateTips(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Tips>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in REST");
            }
        });

    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_tips, menu);
        //getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuMyTips) {
            Intent i=new Intent(this.getContext(), MyTipsActivity.class);
            i.putExtra("IDUSER", retornaIDuser());
            this.startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void showFABMenu(){
        isFABOpen=true;
        ll2.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        ll3.animate().translationY(-getResources().getDimension(R.dimen.standard_105));

        lbl1.setVisibility(View.VISIBLE);
        lbl2.setVisibility(View.VISIBLE);
    }

    private void closeFABMenu(){
        isFABOpen=false;
        ll2.animate().translationY(0);
        ll3.animate().translationY(0);
        lbl1.setVisibility(View.INVISIBLE);
        lbl2.setVisibility(View.INVISIBLE);
    }

}