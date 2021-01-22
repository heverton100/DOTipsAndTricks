package com.example.dotipsandtricks.ui.adapters.ship;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Ships;
import com.example.dotipsandtricks.ui.ItemClickListener;
import com.example.dotipsandtricks.ui.activities.ship.ShipActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ShipAdapter extends RecyclerView.Adapter<ShipAdapter.ViewHolder> {

    Context mContext;
    private List<Ships> mNaves;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtTitle, txtDescNave;
        ItemClickListener itemClickListener;
        public ImageView imageViewNave;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvNave);
            imageViewNave = itemView.findViewById(R.id.ivNave);
            txtDescNave = itemView.findViewById(R.id.tvDescNave);

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

    public ShipAdapter(Context context, List<Ships> naves) {
        mNaves = naves;
        mContext = context;
    }

    @NonNull
    @Override
    public ShipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View naveView = inflater.inflate(R.layout.cell_ship, parent, false);

        return new ShipAdapter.ViewHolder(naveView);
    }

    @Override
    public void onBindViewHolder(ShipAdapter.ViewHolder holder, int position) {

        final Ships nave = mNaves.get(position);
        TextView textView = holder.txtTitle;
        textView.setText(nave.getNameShip());

        TextView txtDescNave = holder.txtDescNave;
        txtDescNave.setText(nave.getDescriptionShip());

        ImageView iv = holder.imageViewNave;
        Picasso.get().load(nave.getImageShip()).into(iv);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(nave.getIdShip().toString(),nave.getNameShip());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mNaves.size();
    }

    public void updateNaves(List<Ships> naves) {
        mNaves = naves;
        notifyDataSetChanged();
    }


    public void openDetailActivity(String x,String y) {
        Intent i=new Intent(mContext, ShipActivity.class);
        i.putExtra("ID_NAVE", Integer.parseInt(x));
        i.putExtra("SHIP_NAME",y);
        mContext.startActivity(i);
    }

}

