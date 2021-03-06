package com.example.dotipsandtricks.ui.adapters.item;

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
import com.example.dotipsandtricks.model.Items;
import com.example.dotipsandtricks.ui.ItemClickListener;
import com.example.dotipsandtricks.ui.activities.item.ItemDetailsActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    Context mContext;
    private List<Items> mItems;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtTitle, txtType;
        public ImageView imageViewItem;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {

            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvItemName);
            imageViewItem = itemView.findViewById(R.id.ivItem);
            txtType = itemView.findViewById(R.id.tvDamageBase);

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

    public ItemsAdapter(Context context, List<Items> items) {
        mItems = items;
        mContext = context;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View moduloView = inflater.inflate(R.layout.cell_item, parent, false);

        return new ItemsAdapter.ViewHolder(moduloView);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {

        final Items items = mItems.get(position);
        TextView textView = holder.txtTitle;
        textView.setText(items.getNameItem());

        ImageView iv = holder.imageViewItem;
        Picasso.get().load(items.getUrlImageItem()).into(iv);

        if (items.getIdCategory() == 11){
            TextView txType = holder.txtType;
            txType.setText("Damage Base: "+items.getDamageBaseLasers());
        }else{
            TextView txType = holder.txtType;
            txType.setVisibility(View.GONE);
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailItemActivity(items.getIdItem().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateItems(List<Items> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void openDetailItemActivity(String x) {
        Intent i=new Intent(mContext, ItemDetailsActivity.class);
        i.putExtra("ID_ITEM", Integer.parseInt(x));
        mContext.startActivity(i);
    }

}
