package com.example.grocersdeliveryhelper.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.grocersdeliveryhelper.Model.DefaultResponse;
import com.example.grocersdeliveryhelper.Model.User;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.api.Api;
import com.example.grocersdeliveryhelper.api.RetrofitClient;
import com.example.grocersdeliveryhelper.storage.SharedPrefManager;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePassword extends AppCompatActivity {

    EditText editPassword,editConfirmPassword;
    Context context;
    Button btnProceed;
    ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;
    String decryptedToken,contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        editPassword = findViewById(R.id.editPass);
        editConfirmPassword = findViewById(R.id.editConfirmPass);
        btnProceed = findViewById(R.id.btn_proceed);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
            }
        });

        editPassword .setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (editPassword .getRight() - editPassword .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        editPassword .setTransformationMethod(null) ;
                        return true;
                    }
                }
                editPassword .setTransformationMethod(new PasswordTransformationMethod());
                return false;
            }
        });

        editConfirmPassword .setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (editConfirmPassword .getRight() - editConfirmPassword .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        editConfirmPassword .setTransformationMethod(null) ;
                        return true;
                    }
                }
                editConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
                return false;
            }
        });


        contact=getIntent().getStringExtra("contact");
    }

    private void updatePassword() {
        String currentpassword = editPassword.getText().toString();
        String newpassword = editConfirmPassword.getText().toString();

        if (currentpassword.isEmpty()) {
            editPassword.setError("This field is required");
            editPassword.requestFocus();
            return;
        }

        if (newpassword.isEmpty()) {
            editConfirmPassword.setError("This field is required");
            editConfirmPassword.requestFocus();
            return;
        }

        if(!currentpassword.equals(newpassword))
        {
            Log.d("cpwd", currentpassword);
            Log.d("npwd", newpassword);

            editConfirmPassword.setError("Passwords doesn't match");
            editConfirmPassword.requestFocus();
            return;
        }


        sharedPrefManager = new SharedPrefManager(this);
        decryptedToken = sharedPrefManager.getToken();

        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
        final Api api =retrofitClient.create(Api.class);
        Call<DefaultResponse> call = api.updatePassword(contact,editPassword.getText().toString());


        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                DefaultResponse d=response.body();
                if(d.getState().equals("success")){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ChangePassword.this,d.getMsg()+"",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePassword.this, Login.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
