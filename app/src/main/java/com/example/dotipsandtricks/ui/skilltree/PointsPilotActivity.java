package com.example.dotipsandtricks.ui.skilltree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.PointsPilot;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointsPilotActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PostService mService;
    private PointsPilotAdapter ppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_pilot);

        getSupportActionBar().setTitle("Cost PPs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.rvPontosPesquisa);

        ppAdapter = new PointsPilotAdapter(this, new ArrayList<PointsPilot>(0));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(ppAdapter);
        mRecyclerView.setHasFixedSize(true);

        mService = ApiUtils.getPostService();

        loadPPs();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTextDialog();
            }
        });
    }

    private void loadPPs() {

        Call<List<PointsPilot>> call = mService.getPP();

        call.enqueue(new Callback<List<PointsPilot>>() {
            @Override
            public void onResponse(Call<List<PointsPilot>> call, Response<List<PointsPilot>> response) {

                if(response.isSuccessful()) {
                    ppAdapter.updatePPs(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<PointsPilot>> call, Throwable t) {
                Log.d("MainActivity", "Erro na chamada REST");
            }
        });
    }

    private void showTextDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter the number of PPs you have:");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String teste = input.getText().toString();
                loadPPsFiltro(teste);

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

    private void loadPPsFiltro(String i) {

        Call<List<PointsPilot>> call = mService.getPPfiltro(i);

        call.enqueue(new Callback<List<PointsPilot>>() {
            @Override
            public void onResponse(Call<List<PointsPilot>> call, Response<List<PointsPilot>> response) {

                if(response.isSuccessful()) {
                    ppAdapter.updatePPs(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<PointsPilot>> call, Throwable t) {
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