package com.example.grocersdeliveryhelper.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.example.grocersdeliveryhelper.Model.LoginResponse;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.Utilities.CheckInternetConnection;
import com.example.grocersdeliveryhelper.api.Api;
import com.example.grocersdeliveryhelper.api.RetrofitClient;
import com.example.grocersdeliveryhelper.storage.SharedPrefManager;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity implements View.OnClickListener {



    private EditText editTextName;
    private EditText editTextPassword;
    private TextView txtForgotPass;
    private ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextName = findViewById(R.id.editName);
        editTextPassword = findViewById(R.id.editPass);
        txtForgotPass =findViewById(R.id.txtForgotPass);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
//        findViewById(R.id.txtSignIn).setOnClickListener(this);
        findViewById(R.id.txtForgotPass).setOnClickListener(this);
        sharedPrefManager= new SharedPrefManager(Login.this);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //your method
                if(CheckInternetConnection.checkConnection(Login.this))
                {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            new Timer().cancel();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        }
                    });

                }
                if(!CheckInternetConnection.checkConnection(Login.this))
                {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(Login.this, "Internet connectivity is not available ", Toast.LENGTH_SHORT).show();

                        }
                    });
                }


            }
        }, 0, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin(){

        String email = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextName.setError("Email is required");
            editTextName.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextName.setError("Enter a valid email");
            editTextName.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
        final Api api =retrofitClient.create(Api.class);
        Call<LoginResponse> call = api.userLogin(email,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(loginResponse.getState().equals("success"))
                {
                    sharedPrefManager.saveToken(loginResponse.getToken());
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(Login.this,Home.class));
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(Login.this,"Invalid credentials !",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this,"Something went wrong ! Try after sometime",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        new Timer().cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Timer().cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        new Timer().cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new Timer().cancel();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSignIn:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.txtForgotPass:
                startActivity(new Intent(this,OTP.class));
                break;
        }

    }
}
