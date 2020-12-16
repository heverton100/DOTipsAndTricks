package com.example.dotipsandtricks.ui.items;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Items;
import com.example.dotipsandtricks.model.Assembly;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsActivity extends AppCompatActivity {

    TextView titleTxt,itemDescTxt;
    ImageView itemIv;
    private PostService mService;

    Button arrowBtnMont;
    ConstraintLayout expandableViewMont;
    CardView cardViewMont;
    ListView listViewMont;

    List<Assembly> assemblies;
    ItemAssemblyAdapter itemAssemblyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        getSupportActionBar().setTitle("Item details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleTxt = findViewById(R.id.tvItemNameDT);
        itemDescTxt = findViewById(R.id.tvItemDescDT);
        itemIv = findViewById(R.id.ivItemDT);

        arrowBtnMont = findViewById(R.id.arrowBtnMontagem);
        expandableViewMont = findViewById(R.id.expandableViewMontagem);
        cardViewMont = findViewById(R.id.cardViewMontagem);
        listViewMont = findViewById(R.id.listMontagem);

        mService = ApiUtils.getPostService();

        Intent i=this.getIntent();
        final Integer title = i.getIntExtra("IDITEM",0);

        Call<Items> call = mService.getItem(title);

        call.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {

                if(response.isSuccessful()) {
                    titleTxt.setText(response.body().getNomeItem());
                    itemDescTxt.setText(response.body().getDescricaoItem());

                    Picasso.get().load(response.body().getUrlImageItem()).into(itemIv);

                    if(response.body().getExisteMontagem().trim().equals("S")){
                        cardViewMont.setVisibility(View.VISIBLE);
                    }
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST"+t);
            }
        });

        arrowBtnMont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableViewMont.getVisibility()==View.GONE){

                    TransitionManager.beginDelayedTransition(cardViewMont, new AutoTransition());
                    expandableViewMont.setVisibility(View.VISIBLE);
                    arrowBtnMont.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                    retornaMontagem(title);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewMont, new AutoTransition());
                    expandableViewMont.setVisibility(View.GONE);
                    arrowBtnMont.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
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

    private void retornaMontagem(Integer itemId) {

        Call<List<Assembly>> call = mService.getMontagem(itemId);

        call.enqueue(new Callback<List<Assembly>>() {
            @Override
            public void onResponse(Call<List<Assembly>> call, Response<List<Assembly>> response) {

                if(response.isSuccessful()) {

                    assemblies = response.body();
                    itemAssemblyAdapter = new ItemAssemblyAdapter(getApplicationContext(), R.layout.cell_assembly, assemblies);
                    listViewMont.setAdapter(itemAssemblyAdapter);

                    listViewMont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(ItemDetailsActivity.this, assemblies.get(i).getDescricaoItem(),Toast.LENGTH_LONG).show();
                        }
                    });

                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Assembly>> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });
    }

}