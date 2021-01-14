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

    TextView hpTxt,velocTxt,cargaTxt,habTxt,bonusTxt,lasersTxt,geradoresTxt,extrasTxt,modulosTxt;
    ImageView naveIv;
    private PostService mService;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ship_details, container, false);

        mService = ApiUtils.getPostService();

        hpTxt = root.findViewById(R.id.txtHpDT);
        velocTxt = root.findViewById(R.id.txtVelocDT);
        cargaTxt = root.findViewById(R.id.txtCargaDT);
        habTxt = root.findViewById(R.id.txtHabDT);
        bonusTxt = root.findViewById(R.id.txtBonusDT);
        lasersTxt = root.findViewById(R.id.txtLasersDT);
        geradoresTxt = root.findViewById(R.id.txtGeradoresDT);
        extrasTxt = root.findViewById(R.id.txtExtrasDT);
        modulosTxt = root.findViewById(R.id.txtModulosDT);
        naveIv = root.findViewById(R.id.ivNaveDT);

        Intent i = this.getActivity().getIntent();
        Integer title = i.getIntExtra("IDNAVE",0);


        Log.d("TESSSSSSSS", "Chamada REST retornou: "+title);

        Call<Ships> call = mService.getNave(title);

        call.enqueue(new Callback<Ships>() {
            @Override
            public void onResponse(@NonNull Call<Ships> call, @NonNull Response<Ships> response) {

                if(response.isSuccessful()) {

                    hpTxt.setText(response.body().getPontosHp().toString());
                    velocTxt.setText(response.body().getVelocidade().toString());
                    cargaTxt.setText(response.body().getCarga().toString());
                    habTxt.setText(response.body().getTemHabilidade());
                    bonusTxt.setText(response.body().getBonus());
                    lasersTxt.setText(response.body().getSlotsLaser().toString());
                    geradoresTxt.setText(response.body().getSlotsGeradores().toString());
                    extrasTxt.setText(response.body().getSlotsExtras().toString());
                    modulosTxt.setText(response.body().getSlotsModulosNave().toString());

                    Picasso.get().load(response.body().getImageNave()).into(naveIv);

                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Ships> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST"+t);
            }
        });

        return root;
    }
}