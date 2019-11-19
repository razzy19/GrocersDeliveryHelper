package com.example.grocersdeliveryhelper.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grocersdeliveryhelper.Model.DefaultResponse;
import com.example.grocersdeliveryhelper.Model.User;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.api.Api;
import com.example.grocersdeliveryhelper.api.RetrofitClient;
import com.example.grocersdeliveryhelper.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePassword extends AppCompatActivity {

    EditText editTextCurrentPassword,editTextNewPassword;
    Context context;
    Button btnProceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        editTextCurrentPassword = findViewById(R.id.editPass);
        editTextNewPassword = findViewById(R.id.editConfirmPass);
        btnProceed = findViewById(R.id.btn_proceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
            }
        });

    }

    private void updatePassword() {
        String currentpassword = editTextCurrentPassword.getText().toString().trim();
        String newpassword = editTextNewPassword.getText().toString().trim();

        if (currentpassword.isEmpty()) {
            editTextCurrentPassword.setError("Password required");
            editTextCurrentPassword.requestFocus();
            return;
        }

        if (newpassword.isEmpty()) {
            editTextNewPassword.setError("Enter new password");
            editTextNewPassword.requestFocus();
            return;
        }


//        User user = SharedPrefManager.getInstance(context).getUser();

//        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
//        final Api api =retrofitClient.create(Api.class);
//        Call<DefaultResponse> call = api.updatePassword(currentpassword,newpassword,user.getEmail());
//
//
//        call.enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//
//                DefaultResponse d=response.body();
//                if(d.getMsg().equals("success")){
//                    Intent intent = new Intent(ChangePassword.this, Home.class);
//                    startActivity(intent);
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//
//                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
//
//
//            }
//        });
    }
}
