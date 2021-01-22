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
    private List<CategoryItem> mCategories;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtTitle;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtCategoryItems);
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

    public CategoryItemsAdapter(Context context, List<CategoryItem> categories) {
        mCategories = categories;
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

        final CategoryItem category = mCategories.get(position);
        TextView textView = holder.txtTitle;
        textView.setText(category.getDescriptionCategory());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openItemsActivity(category.getIdCategory().toString(),category.getDescriptionCategory());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void updateCategories(List<CategoryItem> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    public void openItemsActivity(String x, String y) {
        Intent i=new Intent(mContext, ItemsActivity.class);
        i.putExtra("ID_CATEGORY", Integer.parseInt(x));
        i.putExtra("NAME_CATEGORY", y);
        mContext.startActivity(i);
    }
}
