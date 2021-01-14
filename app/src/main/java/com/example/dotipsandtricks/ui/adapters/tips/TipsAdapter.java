package com.example.dotipsandtricks.ui.adapters.tips;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Tips;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    List<Tips> mTips;
    Context mContext;

    private PostService mService;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtCat,txtAut,txtCont,txtLikes;

        public ImageView like,tipImage,checkImage;

        public ViewHolder(View itemView) {

            super(itemView);
            txtCat = itemView.findViewById(R.id.txtTipCategory);
            txtAut = itemView.findViewById(R.id.txtTipAuthor);
            txtCont = itemView.findViewById(R.id.txtTipContent);
            txtLikes = itemView.findViewById(R.id.txtlikes);

            like = itemView.findViewById(R.id.ivLiked);

            tipImage = itemView.findViewById(R.id.ivImageTip);
            checkImage = itemView.findViewById(R.id.ivDialogImage);

        }

    }

    public TipsAdapter(Context context, List<Tips> tips){
        mTips = tips;
        mContext = context;
    }

    @NonNull
    @Override
    public TipsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View TipView = inflater.inflate(R.layout.cell_tip, parent, false);

        return new TipsAdapter.ViewHolder(TipView);
    }

    @Override
    public void onBindViewHolder(final TipsAdapter.ViewHolder holder, final int position) {

        mService = ApiUtils.getPostService();

        final Tips tip = mTips.get(position);
        TextView textView1 = holder.txtCat;
        textView1.setText(tip.getCategoryTip());

        TextView textView2 = holder.txtAut;
        textView2.setText(tip.getAuthor());

        TextView textView3 = holder.txtCont;

        ImageView iTip = holder.tipImage;
        ImageView ivDialog = holder.checkImage;

        if(!tip.getContentTip().equals("")){
            textView3.setText(tip.getContentTip());
            textView3.setVisibility(View.VISIBLE);
        }else{
            Picasso.get().load(tip.getUrlImage()).into(iTip);
            iTip.setVisibility(View.VISIBLE);
        }

        if(!tip.getContentTip().equals("") && !tip.getUrlImage().equals("")){
            ivDialog.setVisibility(View.VISIBLE);
            ivDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImageDialog(tip.getUrlImage());
                }
            });
        }

        TextView textView4 = holder.txtLikes;
        textView4.setText(tip.getLikes().toString());

        final ImageView iv = holder.like;

        if(tip.getLiked() == 1){
            iv.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else if(tip.getLiked() == 0){
            iv.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tip.getLiked() == 0){
                    newLike(tip.getIdTip(),retornaIDuser());
                    tip.setLiked(1);
                    tip.setLikes(tip.getLikes()+1);
                    notifyItemChanged(position);
                    iv.setImageResource(R.drawable.ic_baseline_favorite_24);
                }

            }
        });

        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if(tip.getLiked() == 1){
                    desLike(tip.getIdTip(),retornaIDuser());
                    tip.setLiked(0);
                    tip.setLikes(tip.getLikes()-1);
                    notifyItemChanged(position);
                    iv.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTips.size();
    }

    public void updateTips(List<Tips> tips) {
        mTips = tips;
        notifyDataSetChanged();
    }

    public void newLike(Integer idtip,Integer iduser) {
        mService.newLike("newlike",idtip,iduser).enqueue(new Callback<Tips>() {
            @Override
            public void onResponse(@NonNull Call<Tips> call, @NonNull Response<Tips> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("failed")) {
                        Toast.makeText(mContext, "Error!", Toast.LENGTH_LONG).show();
                    } else if (response.body().getResponse().equals("success")) {
                        Toast.makeText(mContext, "Successful Like!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Tips> call, @NonNull Throwable t) {
                Log.e("TESTTTTT", "Unable to submit post to API." + t.getMessage());
            }
        });
    }

    public void desLike(Integer idtip,Integer iduser) {
        mService.desLike("deslike",idtip,iduser).enqueue(new Callback<Tips>() {
            @Override
            public void onResponse(@NonNull Call<Tips> call, @NonNull Response<Tips> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("failed")) {
                        Toast.makeText(mContext, "Error!", Toast.LENGTH_LONG).show();
                    } else if (response.body().getResponse().equals("success")) {
                        Toast.makeText(mContext, "Successful unLike!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Tips> call, @NonNull Throwable t) {
                Log.e("TESTTTTT", "Unable to submit post to API." + t.getMessage());
            }
        });
    }

    public Integer retornaIDuser(){
        int id;
        SharedPreferences prefs = mContext.getSharedPreferences("user", Context.MODE_PRIVATE);
        id = prefs.getInt("userid",0);
        return id;
    }

    private void showImageDialog(String x){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        final ImageView iv = new ImageView(mContext);
        iv.setMaxHeight(300);
        Picasso.get().load(x).into(iv);

        builder.setView(iv);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}