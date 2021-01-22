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
import com.example.dotipsandtricks.model.ShipDesigns;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShipDesignAdapter extends RecyclerView.Adapter<ShipDesignAdapter.ViewHolder> {

    List<ShipDesigns> mDesigns;
    Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;
        public ImageView imageViewItem;

        public ViewHolder(View itemView) {

            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtShipDesign);
            imageViewItem = itemView.findViewById(R.id.ivShipDesign);

        }

    }

    public ShipDesignAdapter(Context context, List<ShipDesigns> designs){
        mDesigns = designs;
        mContext = context;
    }

    @NonNull
    @Override
    public ShipDesignAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View designView = inflater.inflate(R.layout.cell_ship_designs, parent, false);

        return new ShipDesignAdapter.ViewHolder(designView);
    }


    @Override
    public void onBindViewHolder(ShipDesignAdapter.ViewHolder holder, int position) {

        ShipDesigns designs = mDesigns.get(position);

        TextView textView = holder.txtTitle;
        textView.setText(designs.getNameDesign());

        ImageView iv = holder.imageViewItem;
        Picasso.get().load(designs.getImageDesign()).into(iv);

    }

    @Override
    public int getItemCount() {
        return mDesigns.size();
    }

    public void updateDesigns(List<ShipDesigns> designs) {
        mDesigns = designs;
        notifyDataSetChanged();
    }

}