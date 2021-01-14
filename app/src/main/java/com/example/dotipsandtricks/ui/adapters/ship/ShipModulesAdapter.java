package com.example.dotipsandtricks.ui.adapters.ship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Modules;
import com.example.dotipsandtricks.ui.ItemClickListener;
import com.squareup.picasso.Picasso;
import java.util.List;


public class ShipModulesAdapter extends RecyclerView.Adapter<ShipModulesAdapter.ViewHolder> {

    List<Modules> mModules;
    Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtTitle;
        public ImageView imageViewItem;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {

            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtModuloListN);
            imageViewItem = itemView.findViewById(R.id.ivModuloListN);

            itemView.setOnClickListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }

    }

    public ShipModulesAdapter(Context context, List<Modules> modules){
        mModules = modules;
        mContext = context;
    }

    @NonNull
    @Override
    public ShipModulesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View moduloView = inflater.inflate(R.layout.cell_modules_ship, parent, false);

        return new ShipModulesAdapter.ViewHolder(moduloView);
    }


    @Override
    public void onBindViewHolder(ShipModulesAdapter.ViewHolder holder, int position) {

        final Modules module = mModules.get(position);
        TextView textView = holder.txtTitle;
        textView.setText(module.getNomeModulo());

        ImageView iv = holder.imageViewItem;
        Picasso.get().load(module.getImageModulo()).into(iv);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(mContext,"Effect: "+module.getDescricaoModulo(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    public void updateModules(List<Modules> modules) {
        mModules = modules;
        notifyDataSetChanged();
    }

}
