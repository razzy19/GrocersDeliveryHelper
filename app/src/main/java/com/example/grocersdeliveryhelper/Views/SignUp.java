package com.example.grocersdeliveryhelper.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.grocersdeliveryhelper.Model.DefaultResponse;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.api.Api;
import com.example.grocersdeliveryhelper.api.RetrofitClient;
import com.example.grocersdeliveryhelper.storage.SharedPrefManager;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextPassword, editTextPhone, editTextAddress,editTextUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        editTextName = findViewById(R.id.editName);
        editTextPassword = findViewById(R.id.editPass);
        editTextPhone = findViewById(R.id.editPhone);
        editTextAddress= findViewById(R.id.editAddress);
        editTextUserID=findViewById(R.id.editUserID);

        findViewById(R.id.btnSignUp).setOnClickListener(this);
        findViewById(R.id.txtSignIn).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void userSignUp() {
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String userid =editTextUserID.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Email is required");
            editTextName.requestFocus();
            return;
        }
        if (userid.isEmpty()) {
            editTextName.setError("UserID is required");
            editTextName.requestFocus();
            return;
        }

     /*   if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }*/

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

        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            editTextAddress.setError("Address required");
            editTextAddress.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            editTextPhone.setError("Phone required");
            editTextPhone.requestFocus();
            return;
        }
        if (phone.length()==10) {
            editTextPhone.setError("Enter valid number");
            editTextPhone.requestFocus();
            return;
        }


        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
        final Api api =retrofitClient.create(Api.class);
        Call<DefaultResponse> call = api.createUser(address,password,name,phone);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {

                    DefaultResponse dr = response.body();
                    Toast.makeText(SignUp.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(SignUp.this, "User already exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                userSignUp();
                break;
            case R.id.txtSignIn:

                startActivity(new Intent(this, Login.class));

                break;
        }

    }
}
