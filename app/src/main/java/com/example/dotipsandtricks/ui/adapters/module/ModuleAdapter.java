package com.example.dotipsandtricks.ui.adapters.module;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
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

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    Context mContext;
    private List<Modules> mModulos;

    List<Ships> naves;

    ModuleShipsAdapter shipsModuleAdapter;
    PostService mService;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle,txtTipo,txtDescricaoModulo;
        Button arrowBtn;
        ConstraintLayout expandableView;
        CardView cardView;
        ListView listView;

        public ImageView imageViewModulo;

        public ViewHolder(View itemView) {

            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvModulo);
            imageViewModulo = itemView.findViewById(R.id.ivModulo);
            txtTipo = itemView.findViewById(R.id.tvTipo);
            txtDescricaoModulo = itemView.findViewById(R.id.tvDescricaoModulo);
            arrowBtn = itemView.findViewById(R.id.arrowBtn);
            expandableView = itemView.findViewById(R.id.expandableView);
            cardView = itemView.findViewById(R.id.cardViewModulo);
            listView = itemView.findViewById(R.id.listNavesModulo);

        }
    }

    public ModuleAdapter(Context context, List<Modules> modulos) {
        mModulos = modulos;
        mContext = context;
    }

    @NonNull
    @Override
    public ModuleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View moduloView = inflater.inflate(R.layout.cell_module, parent, false);

        return new ModuleAdapter.ViewHolder(moduloView);
    }

    @Override
    public void onBindViewHolder(ModuleAdapter.ViewHolder holder, final int position) {

        mService = ApiUtils.getPostService();

        final Modules modulo = mModulos.get(position);
        TextView textView = holder.txtTitle;
        textView.setText(modulo.getNomeModulo());

        TextView txTipo = holder.txtTipo;
        txTipo.setText(modulo.getTipoModulo());

        TextView txDescMod = holder.txtDescricaoModulo;
        txDescMod.setText(modulo.getDescricaoModulo());

        ImageView iv = holder.imageViewModulo;
        Picasso.get().load(modulo.getImageModulo()).into(iv);

        final ConstraintLayout cl = holder.expandableView;
        final Button btnArrow = holder.arrowBtn;
        final CardView cv = holder.cardView;
        final ListView listView = holder.listView;

        btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TESSSSTEE", "Chamada REST retornou: "+ position);

                if (cl.getVisibility()==View.GONE){

                    TransitionManager.beginDelayedTransition(cv, new AutoTransition());
                    cl.setVisibility(View.VISIBLE);
                    btnArrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                    Call<List<Ships>> call = mService.getModuloNaves(modulo.getIdModulo());

                    call.enqueue(new Callback<List<Ships>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Ships>> call, @NonNull Response<List<Ships>> response) {

                            if(response.isSuccessful()) {
                                naves = response.body();
                                shipsModuleAdapter = new ModuleShipsAdapter(mContext, R.layout.cell_modules_ship, naves);
                                listView.setAdapter(shipsModuleAdapter);
                            }else {
                                int statusCode = response.code();
                                Log.d("MainActivity", "Chamada REST retornou: "+statusCode);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Ships>> call, @NonNull Throwable t) {
                            Log.d("MainActivity", "Erro na chamada REST");
                        }
                    });

                } else {
                    TransitionManager.beginDelayedTransition(cv, new AutoTransition());
                    cl.setVisibility(View.GONE);
                    btnArrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModulos.size();
    }

    public void updateModulos(List<Modules> modulos) {
        mModulos = modulos;
        notifyDataSetChanged();
    }

}