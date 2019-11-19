package com.example.grocersdeliveryhelper.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.grocersdeliveryhelper.Controller.CompletedOrdersAdapter;
import com.example.grocersdeliveryhelper.Model.CompletedOrdersModel;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.Response.CompletedOrdersResponse;
import com.example.grocersdeliveryhelper.Response.PendingOrdersResponse;
import com.example.grocersdeliveryhelper.api.Api;
import com.example.grocersdeliveryhelper.api.RetrofitClient;
import com.example.grocersdeliveryhelper.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;


public class CompletedOrders extends Fragment {
    RecyclerView recyclerView;
    ArrayList<CompletedOrdersModel> completedOrdersList;
    CompletedOrdersAdapter completedOrdersAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context;
    SharedPrefManager sharedPrefManager;
    private String decryptedToken;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_completed_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            completedOrders(view);
            swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
           swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
               @Override
               public void onRefresh() {
                   swipeRefreshLayout.setRefreshing(false);
                   completedOrders(view);
               }
           });
    }
    private void completedOrders(View view){

        sharedPrefManager = new SharedPrefManager(this.getActivity());
        decryptedToken = sharedPrefManager.getToken();

        recyclerView = view.findViewById(R.id.recycle_completed_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        Retrofit retrofitClient = new RetrofitClient().getRetrofit();
        final Api api = retrofitClient.create(Api.class);
        Call<CompletedOrdersResponse> call = api.getCompletedOrders(decryptedToken,"completed");

        call.enqueue(new Callback<CompletedOrdersResponse>() {
            @Override
            public void onResponse(Call<CompletedOrdersResponse> call, Response<CompletedOrdersResponse> response) {
                CompletedOrdersResponse completedOrdersResponse= response.body();

                if (completedOrdersResponse.getState().equals("success")) {
                    Log.d("historydetails", completedOrdersResponse.getState());

                } else {
                    Log.d("historydetails", completedOrdersResponse.getState());
                }
            }

            @Override
            public void onFailure(Call<CompletedOrdersResponse> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



//        completedOrdersList =new ArrayList<>();
//        completedOrdersList.add(new CompletedOrdersModel("101","1123","Ramesh","231596466","Bavdhan, Pune","4500","20/8/19","8:10"));
//        completedOrdersList.add(new CompletedOrdersModel("102","1153","Suresh","987987466","H-45,Sector-10,Vashi, Mumbai - 400002","2311","20/8/19","10:45"));
//        completedOrdersList.add(new CompletedOrdersModel("103","1223","Mahesh","894996466","Bavdhan, Pune","650","20/8/19","4:5"));
//        completedOrdersAdapter= new CompletedOrdersAdapter(context, completedOrdersList);
//        recyclerView.setAdapter(completedOrdersAdapter);




//        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
//        final Api api =retrofitClient.create(Api.class);
//        Call<List<CompletedOrdersModel>> call = api.getCompletedOrders("delivered");
//
//        call.enqueue(new Callback<List<CompletedOrdersModel>>() {
//            @Override
//            public void onResponse(Call<List<CompletedOrdersModel>> call, Response<List<CompletedOrdersModel>> response) {
//                Log.d("TAG","Response = "+completedOrdersList);
//                completedOrdersAdapter.setCompletedOrdersList(completedOrdersList);
//            }
//
//            @Override
//            public void onFailure(Call<List<CompletedOrdersModel>> call, Throwable t) {
//                Log.d("TAG","Response = "+t.toString());
//            }
//        });
    }
}
