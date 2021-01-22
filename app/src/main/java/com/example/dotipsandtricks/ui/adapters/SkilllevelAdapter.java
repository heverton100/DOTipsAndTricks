package com.example.dotipsandtricks.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Skilllevels;

import java.util.List;

public class SkilllevelAdapter extends RecyclerView.Adapter<SkilllevelAdapter.ViewHolder> {

    Context mContext;
    private List<Skilllevels> mskills;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSkName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSkName = itemView.findViewById(R.id.txtSkilllevel);
        }

    }

    public SkilllevelAdapter(Context context, List<Skilllevels> skills) {
        mskills = skills;
        mContext = context;
    }

    @NonNull
    @Override
    public SkilllevelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View skillView = inflater.inflate(R.layout.cell_skill_level, parent, false);

        return new SkilllevelAdapter.ViewHolder(skillView);
    }

    @Override
    public void onBindViewHolder(SkilllevelAdapter.ViewHolder holder, int position) {

        final Skilllevels skilllevel = mskills.get(position);

        TextView txskname = holder.txtSkName;

        if(skilllevel.getIdSkill() == 0 || skilllevel.getIdSkill() == 4){
            txskname.setText("Level: "+skilllevel.getLevelSkill().toString()+" | Value: "+skilllevel.getValueSkill().toString()+" HP");
        }else{
            txskname.setText("Level: "+skilllevel.getLevelSkill().toString()+" | Value: "+skilllevel.getValueSkill().toString()+"%");
        }

    }

    @Override
    public int getItemCount() {
        return mskills.size();
    }

    public void updateSkilllevel(List<Skilllevels> skills) {
        mskills = skills;
        notifyDataSetChanged();
    }

}
