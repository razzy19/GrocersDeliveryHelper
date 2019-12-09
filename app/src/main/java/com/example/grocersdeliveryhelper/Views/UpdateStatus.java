package com.example.grocersdeliveryhelper.Views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocersdeliveryhelper.Controller.PendingOrdersAdapter;
import com.example.grocersdeliveryhelper.Model.CompletedOrdersModel;
import com.example.grocersdeliveryhelper.Model.StatusModel;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.Response.OrderDeliveredResponse;
import com.example.grocersdeliveryhelper.Response.PendingOrdersResponse;
import com.example.grocersdeliveryhelper.api.Api;
import com.example.grocersdeliveryhelper.api.RetrofitClient;
import com.example.grocersdeliveryhelper.fragments.CompletedOrders;
import com.example.grocersdeliveryhelper.fragments.PendingOrders;
import com.example.grocersdeliveryhelper.storage.SharedPrefManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateStatus extends AppCompatActivity {

    TextView txtdelivered;
    TextView txtorderid;
    TextView txtnotdelivered;
   TextView txtreturn,txtotp;
     TextView txthold;
     Context mcontext;
     ImageView imgClose;
     String orderid,custname;
     String decryptedToken;
     SharedPrefManager sharedPrefManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_status);
        sharedPrefManager=new SharedPrefManager(UpdateStatus.this);
        decryptedToken=sharedPrefManager.getToken();
        txtorderid = findViewById(R.id.txt_orderid);
         txtdelivered = findViewById(R.id.txt_del);
        txtnotdelivered = findViewById(R.id.txt_notdel);
        imgClose = findViewById(R.id.img_close);

        orderid = getIntent().getStringExtra("orderid");
        custname = getIntent().getStringExtra("custname");
        txtorderid.setText(orderid);

        txtdelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeliveredDialog();            }
        });

        txtnotdelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotDeliveredDialog();
            }
        });

//        txtreturn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showReturnDialog();
//            }
//        });


        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateStatus.this.finish();
            }
        });


    }

    public void showNotDeliveredDialog(){
        final Dialog dialog = new Dialog(UpdateStatus.this); // Context, this, etc.
        dialog.setContentView(R.layout.layout_notdelivered);
        dialog.show();
        final TextView txtreason1 ,txtreason2,txtreason3,txtreason4,txtOrderid,txtCustName;
        final Button btnUpdate,btnCancel;
        txtreason1=dialog.findViewById(R.id.txt_reason1);
        txtreason2=dialog.findViewById(R.id.txt_reason2);
        txtreason3=dialog.findViewById(R.id.txt_reason3);
        txtreason4=dialog.findViewById(R.id.txt_reason4);

        btnCancel=dialog.findViewById(R.id.btnCancel);
        btnUpdate=dialog.findViewById(R.id.btnUpdate);

        txtOrderid=dialog.findViewById(R.id.txt_orderid);
        txtCustName=dialog.findViewById(R.id.txtCustName);

        txtOrderid.setText(orderid);
        txtCustName.setText(custname);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtreason1.getBackground()!=null){
                    Log.d("Reasons:", txtreason1.getText().toString());
                }
                if(txtreason2.getBackground()!=null){
                    Log.d("Reasons:", txtreason2.getText().toString());
                }
                if(txtreason3.getBackground()!=null){
                    Log.d("Reasons:", txtreason3.getText().toString());
                }
                if(txtreason4.getBackground()!=null){
                    Log.d("Reasons:", txtreason4.getText().toString());
                }
            }
        });

        txtreason1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtreason1.getBackground()==null) {
                    txtreason1.setBackgroundResource(R.drawable.highlight);
                } else {
                    txtreason1.setBackgroundResource(0);

                }
            }

        });

        txtreason2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtreason2.getBackground()==null) {
                    txtreason2.setBackgroundResource(R.drawable.highlight);
                } else {
                    txtreason2.setBackgroundResource(0);

                }            }
        });

        txtreason3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtreason3.getBackground()==null) {
                    txtreason3.setBackgroundResource(R.drawable.highlight);
                } else {
                    txtreason3.setBackgroundResource(0);

                }            }
        });

        txtreason4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtreason4.getBackground()==null) {
                    txtreason4.setBackgroundResource(R.drawable.highlight);
                } else {
                    txtreason4.setBackgroundResource(0);

                }            }
        });


    }

    public void showDeliveredDialog() {
        final Dialog dialog = new Dialog(UpdateStatus.this); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_otp);
        dialog.show();
        Button btnCancel, btnVerify;
        btnCancel = dialog.findViewById(R.id.txt_cancel);
        btnVerify = dialog.findViewById(R.id.txt_verify);
        txtotp = dialog.findViewById(R.id.txt_otp);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Retrofit retrofitClient = new RetrofitClient().getRetrofit();
                final Api api = retrofitClient.create(Api.class);
                Call<OrderDeliveredResponse> call = api.completeOrder(decryptedToken, Integer.parseInt(orderid), txtotp.getText().toString());

                call.enqueue(new Callback<OrderDeliveredResponse>() {
                    @Override
                    public void onResponse(Call<OrderDeliveredResponse> call, Response<OrderDeliveredResponse> response) {
                        OrderDeliveredResponse o = response.body();

                        if (o.getState().equals("success")) {
                            Toast.makeText(UpdateStatus.this, o.getMsg(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateStatus.this,Home.class));
                        } else {
                            Toast.makeText(UpdateStatus.this, o.getMsg(), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<OrderDeliveredResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_home, fragment)
                .commit();
    }




}

//    public void showReturnDialog(){
//        final Dialog dialog = new Dialog(UpdateStatus.this); // Context, this, etc.
//        dialog.setContentView(R.layout.layout_return);
//        dialog.show();
//        final TextView txtreason1 ,txtreason2,txtreason3,txtreason4,txtOrderid,txtCustName;
//        final Button btnUpdate,btnCancel;
//        txtreason1=dialog.findViewById(R.id.txt_reason1);
//        txtreason2=dialog.findViewById(R.id.txt_reason2);
//        txtreason3=dialog.findViewById(R.id.txt_reason3);
//        txtreason4=dialog.findViewById(R.id.txt_reason4);
//
//        btnCancel=dialog.findViewById(R.id.btnCancel);
//        btnUpdate=dialog.findViewById(R.id.btnUpdate);
//
//        txtOrderid=dialog.findViewById(R.id.txt_orderid);
//        txtCustName=dialog.findViewById(R.id.txtCustName);
//
//        txtOrderid.setText(orderid);
//        txtCustName.setText(custname);
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtreason1.getBackground()!=null){
//                    Log.d("Reasons:", txtreason1.getText().toString());
//                }
//                if(txtreason2.getBackground()!=null){
//                    Log.d("Reasons:", txtreason2.getText().toString());
//                }
//                if(txtreason3.getBackground()!=null){
//                    Log.d("Reasons:", txtreason3.getText().toString());
//                }
//                if(txtreason4.getBackground()!=null){
//                    Log.d("Reasons:", txtreason4.getText().toString());
//                }
//            }
//        });
//
//        txtreason1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (txtreason1.getBackground()==null) {
//                    txtreason1.setBackgroundResource(R.drawable.highlight);
//                } else {
//                    txtreason1.setBackgroundResource(0);
//
//                }
//            }
//
//        });
//
//        txtreason2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (txtreason2.getBackground()==null) {
//                    txtreason2.setBackgroundResource(R.drawable.highlight);
//                } else {
//                    txtreason2.setBackgroundResource(0);
//
//                }            }
//        });
//
//        txtreason3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (txtreason3.getBackground()==null) {
//                    txtreason3.setBackgroundResource(R.drawable.highlight);
//                } else {
//                    txtreason3.setBackgroundResource(0);
//
//                }            }
//        });
//
//        txtreason4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (txtreason4.getBackground()==null) {
//                    txtreason4.setBackgroundResource(R.drawable.highlight);
//                } else {
//                    txtreason4.setBackgroundResource(0);
//
//                }            }
//        });
//
//
//
//    }



