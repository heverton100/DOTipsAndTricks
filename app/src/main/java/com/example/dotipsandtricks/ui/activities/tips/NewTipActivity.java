package com.example.dotipsandtricks.ui.activities.tips;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.response.ResponseMy;
import com.example.dotipsandtricks.model.Tips;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.ImageService;
import com.example.dotipsandtricks.remote.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewTipActivity extends AppCompatActivity {

    private PostService mService;
    public static final int PICK_IMAGE = 100;
    ImageView iv;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tip);

        getSupportActionBar().setTitle("New Tip");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mService = ApiUtils.getPostService();

        final Spinner spin = findViewById(R.id.catNewTip);
        ArrayAdapter aa = ArrayAdapter.createFromResource(this,R.array.categories_tip,android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        final EditText inputCont = findViewById(R.id.contentNewTip);
        Button btnNewtip = findViewById(R.id.btnNewTip);

        btnNewtip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat = spin.getSelectedItem().toString();
                String cont = inputCont.getText().toString();
                Integer id = retornaIDuser();

                String image = returnUrl();

                if(cat.equals("Select a Category")){
                    Toast.makeText(view.getContext(), "Please, select a category!", Toast.LENGTH_LONG).show();
                }/*else if(TextUtils.isEmpty(cont)){
                    Toast.makeText(view.getContext(), "There are empty fields!", Toast.LENGTH_LONG).show();
                }*/else {
                    newTipAPI(id,cat,cont,image,view.getContext());
                }

            }
        });

        Button btnUp = findViewById(R.id.btnUpload);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });

        iv = findViewById(R.id.ivPreview);
    }

    public Integer retornaIDuser(){
        int id;
        SharedPreferences prefs = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        id = prefs.getInt("userid",0);
        return id;
    }


    public void newTipAPI(Integer id,String cat,String cont,String urlImage, final Context c) {
        mService.newTip("newtip",id,cat,cont,urlImage).enqueue(new Callback<Tips>() {
            @Override
            public void onResponse(@NonNull Call<Tips> call, @NonNull Response<Tips> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("failed")) {
                        Toast.makeText(c, "Error!", Toast.LENGTH_LONG).show();
                    } else if (response.body().getResponse().equals("success")) {
                        Toast.makeText(c, "Successful Post!", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Tips> call, @NonNull Throwable t) {
                Log.e("TESTTTTT", "Unable to submit post to API." + t.getMessage());
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:

                onBackPressed();

                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            uploadFile(selectedImage, "2695e11befcab2be06f3ab072ef46067");
        }
    }

    private void uploadFile(Uri fileUri, String desc) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        //creating a file
        File file = new File(getRealPathFromURI(fileUri));

        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), desc);

        //The gson builder
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //creating retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgbb.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //creating our api
        ImageService api = retrofit.create(ImageService.class);

        //creating a call and calling the upload image method
        Call<ResponseMy> call = api.postImage(requestFile, descBody);

        //finally performing the call
        call.enqueue(new Callback<ResponseMy>() {
            @Override
            public void onResponse(@NonNull Call<ResponseMy> call, @NonNull Response<ResponseMy> response) {
                if (response.isSuccessful()) {
                    Picasso.get().load(response.body().getData().getUrl()).into(iv);
                    iv.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                    x = response.body().getData().getUrl();
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseMy> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public String returnUrl(){
        String x = this.x;
        return x;
    }
}