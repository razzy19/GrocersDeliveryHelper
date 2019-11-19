package com.example.grocersdeliveryhelper.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.grocersdeliveryhelper.Model.DefaultResponse;
import com.example.grocersdeliveryhelper.Model.LoginResponse;
import com.example.grocersdeliveryhelper.Model.User;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.Views.Home;
import com.example.grocersdeliveryhelper.Views.Login;
import com.example.grocersdeliveryhelper.api.Api;
import com.example.grocersdeliveryhelper.api.RetrofitClient;
import com.example.grocersdeliveryhelper.storage.SharedPrefManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Account extends Fragment implements View.OnClickListener{

    private EditText editTextEmail, editTextPhone, editTextAddress;
    private EditText editTextCurrentPassword, editTextNewPassword;

    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextCurrentPassword = view.findViewById(R.id.editTextCurrentPassword);
        editTextNewPassword = view.findViewById(R.id.editTextNewPassword);

        view.findViewById(R.id.buttonSave).setOnClickListener(this);
        view.findViewById(R.id.buttonChangePassword).setOnClickListener(this);
        view.findViewById(R.id.buttonDelete).setOnClickListener(this);
    }

    private void updateProfile() {
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Enter a valid email");
//            editTextEmail.requestFocus();
//            return;
//        }

        if (phone.isEmpty()) {
            editTextPhone.setError("Name required");
            editTextPhone.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            editTextAddress.setError("School required");
            editTextAddress.requestFocus();
            return;
        }

//        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
        final Api api =retrofitClient.create(Api.class);
//        Call<LoginResponse> call = api.updateUser(user.getId(),email,address,phone);
//
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

//                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//
//                if (!response.body().isError()) {
//                    SharedPrefManager.getInstance(getActivity()).saveUser(response.body().getUser());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//
//            }
//        });
//    }

//           private void updatePassword() {
//        String currentpassword = editTextCurrentPassword.getText().toString().trim();
//        String newpassword = editTextNewPassword.getText().toString().trim();
//
//        if (currentpassword.isEmpty()) {
//            editTextCurrentPassword.setError("Password required");
//            editTextCurrentPassword.requestFocus();
//            return;
//        }
//
//        if (newpassword.isEmpty()) {
//            editTextNewPassword.setError("Enter new password");
//            editTextNewPassword.requestFocus();
//            return;
//        }


//        User user = SharedPrefManager.getInstance(getActivity()).getUser();


//
//        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
//        final Api api =retrofitClient.create(Api.class);
//        Call<DefaultResponse> call = api.updatePassword(currentpassword,newpassword,user.getEmail());


//        call.enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//
//                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//
//            }
//        });
//    }

//    private void logout() {
//        SharedPrefManager.getInstance(getActivity()).clear();
//        Intent intent = new Intent(getActivity(), Login.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }
//
//    private void deleteUser() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Are you sure?");
//        builder.setMessage("This action is irreversible...");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                User user = SharedPrefManager.getInstance(getActivity()).getUser();
//
//
//                Retrofit retrofitClient =new RetrofitClient().getRetrofit();
//                final Api api =retrofitClient.create(Api.class);
//                Call<DefaultResponse> call = api.deleteUser(user.getId());
//
//                call.enqueue(new Callback<DefaultResponse>() {
//                    @Override
//                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//
//                        if (!response.body().isErr()) {
//                            SharedPrefManager.getInstance(getActivity()).clear();
//                            SharedPrefManager.getInstance(getActivity()).clear();
//                            Intent intent = new Intent(getActivity(), Home.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
//                        }
//
//                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
//
//                    }
//                });
//
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        AlertDialog ad = builder.create();
//        ad.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                 updateProfile();
                break;
            case R.id.buttonChangePassword:
//                updatePassword();
                break;

            case R.id.buttonDelete:
//                  deleteUser();
                break;
        }
    }
}
