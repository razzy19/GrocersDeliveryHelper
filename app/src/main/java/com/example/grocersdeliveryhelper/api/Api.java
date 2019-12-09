package com.example.grocersdeliveryhelper.api;




import com.example.grocersdeliveryhelper.Model.CompletedOrdersModel;
import com.example.grocersdeliveryhelper.Model.DefaultResponse;
import com.example.grocersdeliveryhelper.Model.LoginResponse;
import com.example.grocersdeliveryhelper.Model.StatusModel;
import com.example.grocersdeliveryhelper.Model.UsersResponse;
import com.example.grocersdeliveryhelper.Response.CompletedOrdersResponse;
import com.example.grocersdeliveryhelper.Response.OrderDeliveredResponse;
import com.example.grocersdeliveryhelper.Response.PendingOrdersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("address") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("delivery_agents/login/")
    Call<LoginResponse> userLogin(
            @Field("contact") String email,
            @Field("password") String password
    );

    @GET("allusers")
    Call<UsersResponse> getUsers();


    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("address") String email,
            @Field("name") String name,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("delivery_agents/change_pwd/")
    Call<DefaultResponse> updatePassword(
            @Field("contact") String contact,
            @Field("password") String password
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);

    @FormUrlEncoded
    @POST("orders/get_completed_orders/")
    Call<CompletedOrdersResponse> getCompletedOrders(@Header("Authorization") String token, @Field("status") String status);

    @FormUrlEncoded
    @POST("orders/finish_order/")
    Call<OrderDeliveredResponse> completeOrder(@Header("Authorization") String token, @Field("orderid") int id,@Field("otp") String otp);


    @FormUrlEncoded
    @POST("orders/get_pending_orders/")
    Call<PendingOrdersResponse> getPendingOrders(@Header("Authorization") String token, @Field("status") String status);

    @FormUrlEncoded
    @POST("Orders/update?")
    Call<List<StatusModel>> updateStatus(@Field("status") String status);



}
