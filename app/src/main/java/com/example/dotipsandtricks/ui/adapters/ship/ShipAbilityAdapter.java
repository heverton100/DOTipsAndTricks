package com.example.dotipsandtricks.ui.adapters.ship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.ShipAbilities;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ShipAbilityAdapter extends RecyclerView.Adapter<ShipAbilityAdapter.ViewHolder> {

    List<ShipAbilities> mAbilities;
    Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle,txtAbDesc;
        public ImageView imageViewItem;

        public ViewHolder(View itemView) {

            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtAbName);
            imageViewItem = itemView.findViewById(R.id.ivShipAb);
            txtAbDesc = itemView.findViewById(R.id.txtAbDesc);

        }

    }

    public ShipAbilityAdapter(Context context, List<ShipAbilities> abilities){
        mAbilities = abilities;
        mContext = context;
    }

    @NonNull
    @Override
    public ShipAbilityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View abilityView = inflater.inflate(R.layout.cell_ship_ability, parent, false);

        return new ShipAbilityAdapter.ViewHolder(abilityView);
    }


    @Override
    public void onBindViewHolder(ShipAbilityAdapter.ViewHolder holder, int position) {

        ShipAbilities abilities = mAbilities.get(position);

        TextView textView = holder.txtTitle;
        textView.setText(abilities.getAbilityName());

        TextView textView2 = holder.txtAbDesc;
        textView2.setText(abilities.getAbilityDesc());

        ImageView iv = holder.imageViewItem;
        Picasso.get().load(abilities.getAbilityImage()).into(iv);

    }

    @Override
    public int getItemCount() {
        return mAbilities.size();
    }

    public void updateAbilities(List<ShipAbilities> abilities) {
        mAbilities = abilities;
        notifyDataSetChanged();
    }

}
