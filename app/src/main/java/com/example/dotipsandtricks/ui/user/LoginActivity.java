package com.example.dotipsandtricks.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dotipsandtricks.MainActivity;
import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.model.Users;
import com.example.dotipsandtricks.remote.ApiUtils;
import com.example.dotipsandtricks.remote.PostService;
import com.example.dotipsandtricks.ui.OnSwipeTouchListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private PostService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usermail = findViewById(R.id.userEmail);
        final EditText userpass = findViewById(R.id.userPass);
        Button btnLogin = findViewById(R.id.btnLogin);

        mService = ApiUtils.getPostService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = usermail.getText().toString().trim();
                final String pass = userpass.getText().toString().trim();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
                    mService.login(email,pass).enqueue(new Callback<Users>() {
                        @Override
                        public void onResponse(Call<Users> call, Response<Users> response) {
                            if(response.isSuccessful()) {
                                if(response.body().getResponse().equals("failed")) {
                                    Toast.makeText(LoginActivity.this,"Incorrect email or pass!",Toast.LENGTH_LONG).show();
                                }else if(response.body().getResponse().equals("success")){

                                    SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username",response.body().getUserName());
                                    editor.putInt("userid",response.body().getUserId());
                                    editor.commit();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivityForResult(intent,1);
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<Users> call, Throwable t) {
                            Log.e("TESTTTTT", "Unable to submit post to API."+t);
                        }
                    });
                }
            }
        });


        ConstraintLayout cl = findViewById(R.id.clLogin);
        cl.setOnTouchListener(new OnSwipeTouchListener(LoginActivity.this) {
            public void onSwipeRight() {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });

        TextView fp = findViewById(R.id.txtForgotPass);

        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}