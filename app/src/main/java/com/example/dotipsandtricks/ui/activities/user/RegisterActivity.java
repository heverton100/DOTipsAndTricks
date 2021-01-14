package com.example.dotipsandtricks.ui.activities.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Users;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.OnSwipeTouchListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private PostService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mService = ApiUtils.getPostService();

        final EditText usermail = findViewById(R.id.userEmailR);
        final EditText userpass = findViewById(R.id.userPassR);
        final EditText userpass2 = findViewById(R.id.userPassR2);
        final EditText username = findViewById(R.id.userNameR);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = usermail.getText().toString().trim();
                final String pass = userpass.getText().toString().trim();
                final String pass2 = userpass2.getText().toString().trim();
                final String name = username.getText().toString().trim();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(name)) {
                    if(pass.equals(pass2)) {
                        mService.register(name,email,pass).enqueue(new Callback<Users>() {
                            @Override
                            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getResponse().equals("failed")) {
                                        Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_LONG).show();
                                    } else if (response.body().getResponse().equals("duplicate")) {
                                        Toast.makeText(RegisterActivity.this, "There is already a user with this email!", Toast.LENGTH_LONG).show();
                                    } else if (response.body().getResponse().equals("success")) {
                                        Toast.makeText(RegisterActivity.this, "Successful Registration!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
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
                        Toast.makeText(RegisterActivity.this, "Pass does not match!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Has empty field!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ConstraintLayout cl = findViewById(R.id.clRegister);
        cl.setOnTouchListener(new OnSwipeTouchListener(RegisterActivity.this) {
            public void onSwipeLeft() {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
}