package com.example.dotipsandtricks.ui.ship;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Modules;
import com.example.dotipsandtricks.model.Ships;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipActivity extends AppCompatActivity {

    TextView titleTxt,hpTxt,velocTxt,cargaTxt,habTxt,bonusTxt,lasersTxt,geradoresTxt,extrasTxt,modulosTxt;
    ImageView naveIv;
    ListView listView;
    List<Modules> modulos;
    ModulesShipAdapter modulesShipAdapter;
    private PostService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);

        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listModulosDT);

        mService = ApiUtils.getPostService();

        titleTxt = findViewById(R.id.txtNaveDT);
        hpTxt = findViewById(R.id.txtHpDT);
        velocTxt = findViewById(R.id.txtVelocDT);
        cargaTxt = findViewById(R.id.txtCargaDT);
        habTxt = findViewById(R.id.txtHabDT);
        bonusTxt = findViewById(R.id.txtBonusDT);
        lasersTxt = findViewById(R.id.txtLasersDT);
        geradoresTxt = findViewById(R.id.txtGeradoresDT);
        extrasTxt = findViewById(R.id.txtExtrasDT);
        modulosTxt = findViewById(R.id.txtModulosDT);
        naveIv = findViewById(R.id.ivNaveDT);

        Intent i=this.getIntent();
        Integer title = i.getIntExtra("IDNAVE",0);

        Call<Ships> call = mService.getNave(title);

        call.enqueue(new Callback<Ships>() {
            @Override
            public void onResponse(Call<Ships> call, Response<Ships> response) {

                if(response.isSuccessful()) {

                    titleTxt.setText(response.body().getNome());
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
            public void onFailure(Call<Ships> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST"+t);
            }
        });

        retornaModulos(title);

    }

    private void retornaModulos(Integer naveid) {

        Call<List<Modules>> call = mService.getNaveModulos(naveid);

        call.enqueue(new Callback<List<Modules>>() {
            @Override
            public void onResponse(Call<List<Modules>> call, Response<List<Modules>> response) {

                if(response.isSuccessful()) {

                    if (response.body().get(0).getNomeModulo().equals("TESTE")) {
                        Toast.makeText(getApplicationContext(),"This ship does not have modules!",Toast.LENGTH_SHORT).show();
                    }else{
                        modulos = response.body();
                        modulesShipAdapter = new ModulesShipAdapter(getApplicationContext(), R.layout.list_modules_ship, modulos);
                        listView.setAdapter(modulesShipAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(ShipActivity.this,"Effect: "+modulos.get(i).getDescricaoModulo(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Modules>> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:

                onBackPressed();

                break;
        }
        return true;
    }

}