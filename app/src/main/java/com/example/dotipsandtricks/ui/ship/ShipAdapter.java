package com.example.dotipsandtricks.ui.ship;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Ships;
import com.example.dotipsandtricks.ui.ItemClickListener;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ShipAdapter extends RecyclerView.Adapter<ShipAdapter.ViewHolder> {

    Context mContext;
    private List<Ships> mNaves;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtTitle,txtDescricaoNave;
        ItemClickListener itemClickListener;
        public ImageView imageViewNave;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvNave);
            imageViewNave = itemView.findViewById(R.id.ivNave);
            txtDescricaoNave = itemView.findViewById(R.id.tvDescricaoNave);

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

    @Override
    public ShipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View naveView = inflater.inflate(R.layout.cell_ship, parent, false);

        ViewHolder viewHolder = new ViewHolder(naveView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShipAdapter.ViewHolder holder, int position) {

        final Ships nave = mNaves.get(position);
        TextView textView = holder.txtTitle;
        textView.setText(nave.getNome());

        TextView txtDescNave = holder.txtDescricaoNave;
        txtDescNave.setText(nave.getDescricao());

        ImageView iv = holder.imageViewNave;
        Picasso.get().load(nave.getImageNave()).into(iv);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(nave.getIdNave().toString());
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


    public void openDetailActivity(String x) {
        Intent i=new Intent(mContext, ShipActivity.class);
        i.putExtra("IDNAVE", Integer.parseInt(x));
        mContext.startActivity(i);
    }

}

