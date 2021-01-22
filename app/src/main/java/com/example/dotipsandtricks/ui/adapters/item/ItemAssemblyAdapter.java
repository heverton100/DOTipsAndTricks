package com.example.dotipsandtricks.ui.adapters.item;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Assembly;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAssemblyAdapter extends ArrayAdapter<Assembly> {

    List<Assembly> assemblies;
    Context context;
    int resource;

    public ItemAssemblyAdapter(@NonNull Context context, int resource, @NonNull List<Assembly> objects) {
        super(context, resource, objects);

        this.context = context;
        this.assemblies = objects;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.cell_assembly,null,true);
        }

        Assembly m = getItem(position);

        TextView quantity = convertView.findViewById(R.id.txtQuantityAssembly);
        quantity.setText(m.getQuantity().toString());

        TextView nomeItem = convertView.findViewById(R.id.txtItemAssembly);
        nomeItem.setText(m.getNameItem());

        ImageView iv = convertView.findViewById(R.id.ivItemAssembly);
        Picasso.get().load(m.getUrlImageItem()).into(iv);

        return convertView;
    }

}
