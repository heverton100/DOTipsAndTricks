package com.example.dotipsandtricks.ui.items;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Items;
import com.example.dotipsandtricks.ui.ItemClickListener;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    Context mContext;
    private List<Items> mItens;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtTitle,txtTipo;
        public ImageView imageViewItem;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {

            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvItemName);
            imageViewItem = itemView.findViewById(R.id.ivItem);
            txtTipo = itemView.findViewById(R.id.tvDanoBase);

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

    public ItemsAdapter(Context context, List<Items> itens) {
        mItens = itens;
        mContext = context;
    }

    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View moduloView = inflater.inflate(R.layout.cell_item, parent, false);

        ItemsAdapter.ViewHolder viewHolder = new ItemsAdapter.ViewHolder(moduloView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {

        final Items itens = mItens.get(position);
        TextView textView = holder.txtTitle;
        textView.setText(itens.getNomeItem());

        ImageView iv = holder.imageViewItem;
        Picasso.get().load(itens.getUrlImageItem()).into(iv);

        if (itens.getIdCategoria() == 11){
            TextView txTipo = holder.txtTipo;
            txTipo.setText("Dano Base: "+itens.getDanoBaseLasers());
        }else{
            TextView txTipo = holder.txtTipo;
            txTipo.setVisibility(View.GONE);
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailItemActivity(itens.getIdItem().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }

    public void updateItens(List<Items> itens) {
        mItens = itens;
        notifyDataSetChanged();
    }

    public void openDetailItemActivity(String x) {
        Intent i=new Intent(mContext, ItemDetailsActivity.class);
        i.putExtra("IDITEM", Integer.parseInt(x));
        mContext.startActivity(i);
    }

}
