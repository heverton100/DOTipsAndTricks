package com.example.dotipsandtricks.ui.ship;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Modules;
import com.squareup.picasso.Picasso;
import java.util.List;


public class ModulesShipAdapter extends ArrayAdapter<Modules>{

    List<Modules>modulos;
    Context context;
    int resource;

    public ModulesShipAdapter(@NonNull Context context, int resource, @NonNull List<Modules> modulos) {
        super(context, resource, modulos);

        this.modulos=modulos;
        this.context=context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_modules_ship,null,true);
        }
        Modules modules = getItem(position);
        ImageView img = convertView.findViewById(R.id.ivModuloListN);
        TextView textView = convertView.findViewById(R.id.txtModuloListN);

        Picasso.get().load(modules.getImageModulo()).into(img);
        textView.setText(modules.getNomeModulo());

        return convertView;
    }

}
