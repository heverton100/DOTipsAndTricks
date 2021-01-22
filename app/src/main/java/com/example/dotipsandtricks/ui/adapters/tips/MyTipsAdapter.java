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

public class MyTipsAdapter extends RecyclerView.Adapter<MyTipsAdapter.ViewHolder> {

    List<Tips> mTips;
    Context mContext;

    private PostService mService;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtCat,txtAut,txtCont,txtLikes;
        public ImageView iv,like,tipImage,checkImage;


        public ViewHolder(View itemView) {

            super(itemView);
            txtCat = itemView.findViewById(R.id.txtTipCategory);
            txtAut = itemView.findViewById(R.id.txtTipAuthor);
            txtCont = itemView.findViewById(R.id.txtTipContent);
            txtLikes = itemView.findViewById(R.id.txtlikes);

            iv = itemView.findViewById(R.id.ivmenutip);

            like = itemView.findViewById(R.id.ivLiked);

            tipImage = itemView.findViewById(R.id.ivImageTip);
            checkImage = itemView.findViewById(R.id.ivDialogImage);

        }

    }

    public MyTipsAdapter(Context context, List<Tips> tips){
        mTips = tips;
        mContext = context;
    }

    @NonNull
    @Override
    public MyTipsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View TipView = inflater.inflate(R.layout.cell_tip, parent, false);

        return new MyTipsAdapter.ViewHolder(TipView);
    }

    @Override
    public void onBindViewHolder(MyTipsAdapter.ViewHolder holder, int position) {

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

        ImageView imageView = holder.iv;
        imageView.setVisibility(View.VISIBLE);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTextDialog(tip.getIdTip());
            }
        });

        ImageView ivLik = holder.like;
        if(tip.getLiked() == 1){
            ivLik.setImageResource(R.drawable.ic_baseline_favorite_24);
        }

    }

    @Override
    public int getItemCount() {
        return mTips.size();
    }

    public void updateMyTips(List<Tips> tips) {
        mTips = tips;
        notifyDataSetChanged();
    }

    private void showTextDialog(final Integer idTip){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Select a option:")
        .setItems(R.array.options_tip, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(i==0){
                    stdCheck(idTip);
                }else {
                    dialogInterface.dismiss();
                }

            }
        });
        builder.show();
    }

    private void stdCheck(final Integer idTip){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Are you sure you want to delete this tip?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteTipAPI(idTip);
                        returnTips();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public void deleteTipAPI(Integer id) {
        mService.deleteTip("deletetip",id).enqueue(new Callback<Tips>() {
            @Override
            public void onResponse(@NonNull Call<Tips> call, @NonNull Response<Tips> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("failed")) {
                        Toast.makeText(mContext, "Error!", Toast.LENGTH_LONG).show();
                    } else if (response.body().getResponse().equals("success")) {
                        Toast.makeText(mContext, "Successful delete!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Tips> call, @NonNull Throwable t) {
                Log.e("LOG ERROR", "Unable to submit post to API." + t.getMessage());
            }
        });
    }

    public void returnTips(){

        SharedPreferences prefs = mContext.getSharedPreferences("user", Context.MODE_PRIVATE);
        int id = prefs.getInt("userid",0);

        Call<List<Tips>> call = mService.getMyTips(id,id);

        call.enqueue(new Callback<List<Tips>>() {
            @Override
            public void onResponse(@NonNull Call<List<Tips>> call, @NonNull Response<List<Tips>> response) {

                if(response.isSuccessful()) {
                    updateMyTips(response.body());
                }else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "Call REST return: "+statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Tips>> call, @NonNull Throwable t) {
                Log.d("MainActivity", "Error in Call REST");
            }
        });
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