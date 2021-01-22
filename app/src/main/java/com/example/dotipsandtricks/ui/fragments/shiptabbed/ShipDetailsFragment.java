package com.example.dotipsandtricks.ui.fragments.shiptabbed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Ships;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipDetailsFragment extends Fragment {

    TextView hpTxt, velocityTxt, cargoTxt,habTxt,bonusTxt,lasersTxt, generatorsTxt,extrasTxt, modulesTxt;
    ImageView naveIv;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ship_details, container, false);

        PostService mService = ApiUtils.getPostService();

        hpTxt = root.findViewById(R.id.txtHpDT);
        velocityTxt = root.findViewById(R.id.txtVelocDT);
        cargoTxt = root.findViewById(R.id.txtCargoDT);
        habTxt = root.findViewById(R.id.txtHabDT);
        bonusTxt = root.findViewById(R.id.txtBonusDT);
        lasersTxt = root.findViewById(R.id.txtLasersDT);
        generatorsTxt = root.findViewById(R.id.txtGeneratorsDT);
        extrasTxt = root.findViewById(R.id.txtExtrasDT);
        modulesTxt = root.findViewById(R.id.txtModulesDT);
        naveIv = root.findViewById(R.id.ivNaveDT);

        Intent i = this.getActivity().getIntent();
        Integer title = i.getIntExtra("ID_NAVE",0);

        Call<Ships> call = mService.getShip(title);

        call.enqueue(new Callback<Ships>() {
            @Override
            public void onResponse(@NonNull Call<Ships> call, @NonNull Response<Ships> response) {

                if(response.isSuccessful()) {

                    hpTxt.setText(response.body().getPointsHp().toString());
                    velocityTxt.setText(response.body().getVelocity().toString());
                    cargoTxt.setText(response.body().getCargo().toString());
                    habTxt.setText(response.body().getHasAbility());
                    bonusTxt.setText(response.body().getBonus());
                    lasersTxt.setText(response.body().getSlotsLasers().toString());
                    generatorsTxt.setText(response.body().getSlotsGenerators().toString());
                    extrasTxt.setText(response.body().getSlotsExtras().toString());
                    modulesTxt.setText(response.body().getSlotsModulesShip().toString());

                    Picasso.get().load(response.body().getImageShip()).into(naveIv);

                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Ships> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in Call REST"+t);
            }
        });

        return root;
    }
}