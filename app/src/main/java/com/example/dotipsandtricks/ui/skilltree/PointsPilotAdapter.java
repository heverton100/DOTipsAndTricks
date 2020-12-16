package com.example.dotipsandtricks.ui.skilltree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.PointsPilot;
import java.util.List;

public class PointsPilotAdapter extends RecyclerView.Adapter<PointsPilotAdapter.ViewHolder> {

    Context mContext;
    private List<PointsPilot> mPps;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtPP,txtQtd,txtTotalPP,txtCustoT,txtCustoDesc,txtCustoPrem,txtCustoDescPrem;

        public ViewHolder(View itemView) {
            super(itemView);
            txtPP = itemView.findViewById(R.id.pontop);
            txtQtd = itemView.findViewById(R.id.qtdadePP);
            txtTotalPP = itemView.findViewById(R.id.totalPP);
            txtCustoT = itemView.findViewById(R.id.custoTotal);
            txtCustoDesc = itemView.findViewById(R.id.custoDesconto);
            txtCustoPrem = itemView.findViewById(R.id.custoPremium);
            txtCustoDescPrem = itemView.findViewById(R.id.custoDescPrem);
        }

    }

    public PointsPilotAdapter(Context context, List<PointsPilot> pps) {
        mPps = pps;
        mContext = context;
    }

    @Override
    public PointsPilotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View naveView = inflater.inflate(R.layout.cell_pointpilot, parent, false);

        PointsPilotAdapter.ViewHolder viewHolder = new PointsPilotAdapter.ViewHolder(naveView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PointsPilotAdapter.ViewHolder holder, int position) {

        final PointsPilot pp = mPps.get(position);

        TextView textView = holder.txtPP;
        textView.setText(pp.getPontoPesquisa());

        TextView textView1 = holder.txtQtd;
        textView1.setText(pp.getQtdadeLogs().toString());

        TextView textView2 = holder.txtTotalPP;
        textView2.setText(pp.getTotalLogs().toString());

        TextView textView3 = holder.txtCustoT;
        textView3.setText(pp.getCustoTotal());

        TextView textView4 = holder.txtCustoDesc;
        textView4.setText(pp.getCustoComDesconto());

        TextView textView5 = holder.txtCustoPrem;
        textView5.setText(pp.getCustoComPremium());

        TextView textView6 = holder.txtCustoDescPrem;
        textView6.setText(pp.getCustoDescontoPremium());

    }

    @Override
    public int getItemCount() {
        return mPps.size();
    }

    public void updatePPs(List<PointsPilot> pps) {
        mPps = pps;
        notifyDataSetChanged();
    }

    //public void openDetailActivity(String x) {
    //    Intent i=new Intent(mContext, ShipActivity.class);
    //    i.putExtra("IDNAVE", Integer.parseInt(x));
    //    mContext.startActivity(i);
    //}

}
