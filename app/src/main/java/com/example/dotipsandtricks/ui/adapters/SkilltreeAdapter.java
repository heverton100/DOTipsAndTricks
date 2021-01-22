package com.example.dotipsandtricks.ui.adapters;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Skilllevels;
import com.example.dotipsandtricks.model.Skilltree;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkilltreeAdapter extends RecyclerView.Adapter<SkilltreeAdapter.ViewHolder> {

    Context mContext;
    private List<Skilltree> mskills;

    List<Skilllevels> levels;
    SkilllevelAdapter skilllevelAdapter;
    PostService mService;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSkName,txtSkType,txtSkDesc,txtSkEf;
        public ImageView iv;
        Button arrowBtn;
        ConstraintLayout expandableView;
        CardView cardView;

        RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSkName = itemView.findViewById(R.id.tvSkillName);
            txtSkType = itemView.findViewById(R.id.tvSkillType);
            iv = itemView.findViewById(R.id.ivSkill);
            txtSkDesc = itemView.findViewById(R.id.tvSkillDesc);
            txtSkEf = itemView.findViewById(R.id.tvSkillEffect);

            arrowBtn = itemView.findViewById(R.id.arrowBtnSkill);
            expandableView = itemView.findViewById(R.id.expandableViewSkill);
            cardView = itemView.findViewById(R.id.cardViewSkilltree);

            mRecyclerView = itemView.findViewById(R.id.rvSkLevel);

        }

    }

    public SkilltreeAdapter(Context context, List<Skilltree> skills) {
        mskills = skills;
        mContext = context;
    }

    @NonNull
    @Override
    public SkilltreeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View naveView = inflater.inflate(R.layout.cell_skilltree, parent, false);

        return new SkilltreeAdapter.ViewHolder(naveView);
    }

    @Override
    public void onBindViewHolder(SkilltreeAdapter.ViewHolder holder, final int position) {

        final Skilltree skilltree = mskills.get(position);

        TextView txskname = holder.txtSkName;
        txskname.setText(skilltree.getNameSkill());

        TextView txsktype = holder.txtSkType;
        txsktype.setText(skilltree.getTypeSkill());

        TextView txSkDesc = holder.txtSkDesc;
        txSkDesc.setText(skilltree.getDescriptionSkill());

        TextView txSkEf = holder.txtSkEf;
        txSkEf.setText(skilltree.getVisualEffect());

        ImageView iv = holder.iv;
        final Transformation transformation = new RoundedCornersTransformation(5, 1);
        Picasso.get().load(skilltree.getImageSkill()).transform(transformation).into(iv);


        final ConstraintLayout cl = holder.expandableView;
        final Button btnArrow = holder.arrowBtn;
        final CardView cv = holder.cardView;

        final RecyclerView mRecyclerView = holder.mRecyclerView;

        btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cl.getVisibility()==View.GONE){

                    TransitionManager.beginDelayedTransition(cv, new AutoTransition());
                    cl.setVisibility(View.VISIBLE);
                    btnArrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);


                    skilllevelAdapter = new SkilllevelAdapter(mContext, new ArrayList<Skilllevels>(0));

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(skilllevelAdapter);
                    mRecyclerView.setHasFixedSize(true);

                    mService = ApiUtils.getPostService();

                    Call<List<Skilllevels>> call = mService.getSkilllevels(skilltree.getIdSkill());

                    call.enqueue(new Callback<List<Skilllevels>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Skilllevels>> call, @NonNull Response<List<Skilllevels>> response) {

                            if(response.isSuccessful()) {
                                skilllevelAdapter.updateSkilllevel(response.body());
                            }else {
                                int statusCode = response.code();
                                Log.d("MainActivity", "Call REST return: "+statusCode);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Skilllevels>> call, @NonNull Throwable t) {
                            Log.d("MainActivity", "Error in Call REST");
                        }
                    });

                } else {
                    TransitionManager.beginDelayedTransition(cv, new AutoTransition());
                    cl.setVisibility(View.GONE);
                    btnArrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mskills.size();
    }

    public void updateSkilltree(List<Skilltree> skills) {
        mskills = skills;
        notifyDataSetChanged();
    }

}
