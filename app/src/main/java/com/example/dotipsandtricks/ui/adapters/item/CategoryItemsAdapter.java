package com.example.dotipsandtricks.ui.adapters.item;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.CategoryItem;
import com.example.dotipsandtricks.ui.ItemClickListener;
import com.example.dotipsandtricks.ui.activities.item.ItemsActivity;
import java.util.List;

public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.ViewHolder> {

    Context mContext;
    private List<CategoryItem> mCategorias;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtTitle;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtCategoriaItens);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }

    public CategoryItemsAdapter(Context context, List<CategoryItem> categorias) {
        mCategorias = categorias;
        mContext = context;
    }

    @NonNull
    @Override
    public CategoryItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View naveView = inflater.inflate(R.layout.cell_category, parent, false);

        return new CategoryItemsAdapter.ViewHolder(naveView);
    }

    @Override
    public void onBindViewHolder(CategoryItemsAdapter.ViewHolder holder, int position) {

        final CategoryItem categoria = mCategorias.get(position);
        TextView textView = holder.txtTitle;
        textView.setText(categoria.getDescricaoCategoria());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openItensActivity(categoria.getIdCategoria().toString(),categoria.getDescricaoCategoria());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategorias.size();
    }

    public void updateCategorias(List<CategoryItem> categorias) {
        mCategorias = categorias;
        notifyDataSetChanged();
    }

    public void openItensActivity(String x,String y) {
        Intent i=new Intent(mContext, ItemsActivity.class);
        i.putExtra("IDCATEGORIA", Integer.parseInt(x));
        i.putExtra("NOMECATEGORIA", y);
        mContext.startActivity(i);
    }
}
