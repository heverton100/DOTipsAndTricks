package com.example.dotipsandtricks.ui.adapters.module;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Ships;
import java.util.List;

public class ModuleShipsAdapter extends ArrayAdapter<Ships> {

    List<Ships> naves;
    Context context;
    int resource;

    public ModuleShipsAdapter(@NonNull Context context, int resource, @NonNull List<Ships> naves) {
        super(context, resource, naves);

        this.naves=naves;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_ships_module,null,true);
        }
        Ships ships = getItem(position);
        TextView textView = convertView.findViewById(R.id.txtNaveListM);

        textView.setText(ships.getNameShip());

        return convertView;
    }

}

