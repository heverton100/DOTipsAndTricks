package com.example.dotipsandtricks.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.PointsPilot;
import java.util.List;

public class LogDiskCostChartAdapter extends RecyclerView.Adapter<LogDiskCostChartAdapter.ViewHolder> {

    Context mContext;
    private List<PointsPilot> mPps;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtPP,txtQtd,txtTotalPP, txtCostT, txtCostDesc, txtCostPrem, txtCostDescPrem;

        public ViewHolder(View itemView) {
            super(itemView);
            txtPP = itemView.findViewById(R.id.pointP);
            txtQtd = itemView.findViewById(R.id.quantityPP);
            txtTotalPP = itemView.findViewById(R.id.totalPP);
            txtCostT = itemView.findViewById(R.id.costTotal);
            txtCostDesc = itemView.findViewById(R.id.costDiscount);
            txtCostPrem = itemView.findViewById(R.id.costPremium);
            txtCostDescPrem = itemView.findViewById(R.id.costDescPrem);
        }

    }

    public LogDiskCostChartAdapter(Context context, List<PointsPilot> pps) {
        mPps = pps;
        mContext = context;
    }

    @NonNull
    @Override
    public LogDiskCostChartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View naveView = inflater.inflate(R.layout.cell_pointpilot, parent, false);

        return new LogDiskCostChartAdapter.ViewHolder(naveView);
    }

    @Override
    public void onBindViewHolder(LogDiskCostChartAdapter.ViewHolder holder, int position) {

        final PointsPilot pp = mPps.get(position);

        TextView textView = holder.txtPP;
        textView.setText(pp.getResearchPoint());

        TextView textView1 = holder.txtQtd;
        textView1.setText(pp.getQuantityLogs().toString());

        TextView textView2 = holder.txtTotalPP;
        textView2.setText(pp.getTotalLogs().toString());

        TextView textView3 = holder.txtCostT;
        textView3.setText(pp.getCostTotal());

        TextView textView4 = holder.txtCostDesc;
        textView4.setText(pp.getCostWithDiscount());

        TextView textView5 = holder.txtCostPrem;
        textView5.setText(pp.getCostWithPremium());

        TextView textView6 = holder.txtCostDescPrem;
        textView6.setText(pp.getCostDiscountPremium());

    }

    @Override
    public int getItemCount() {
        return mPps.size();
    }

    public void updatePPs(List<PointsPilot> pps) {
        mPps = pps;
        notifyDataSetChanged();
    }

}
