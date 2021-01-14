package com.example.dotipsandtricks.ui.activities.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Users;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassActivity extends AppCompatActivity {

    private PostService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forgot Pass");

        mService = ApiUtils.getPostService();

        final EditText usermail = findViewById(R.id.userEmailF);
        final EditText userpass = findViewById(R.id.userPassF);
        final EditText userpass2 = findViewById(R.id.userPassF2);
        Button btnForgotPass = findViewById(R.id.btnForgotPass);

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = usermail.getText().toString().trim();
                final String pass = userpass.getText().toString().trim();
                final String pass2 = userpass2.getText().toString().trim();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(pass2)) {
                    if(pass.equals(pass2)) {
                        mService.forgotpass(email,pass).enqueue(new Callback<Users>() {
                            @Override
                            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getResponse().equals("failed")) {
                                        Toast.makeText(ForgotPassActivity.this, "Registration not found!", Toast.LENGTH_LONG).show();
                                    } else if (response.body().getResponse().equals("success")) {
                                        Toast.makeText(ForgotPassActivity.this, "Pass updated successfully!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(ForgotPassActivity.this, LoginActivity.class);
                                        startActivityForResult(intent, 1);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                                Log.e("TESTTTTT", "Unable to submit post to API." + t);
                            }
                        });
                    }else {
                        Toast.makeText(ForgotPassActivity.this, "Pass does not match!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ForgotPassActivity.this, "Has empty field!", Toast.LENGTH_LONG).show();
                }
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
}