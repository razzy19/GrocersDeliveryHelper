package com.example.grocersdeliveryhelper.api;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //    private static final String AUTH = "Basic " + Base64.encodeToString(("pratik:123456").getBytes(), Base64.NO_WRAP);
//
//    //:todo rest api url here
//    private static final String BASE_URL = "";
//    private static RetrofitClient mInstance;
//    private Retrofit retrofit;
//
//
//    private RetrofitClient() {
//        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//
//        // set your desired log level
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        // add logging as last interceptor
//        okHttpClient.addInterceptor(loggingInterceptor);
//
////                .addInterceptor(
////                        new Interceptor() {
////                            @Override
////                            public Response intercept(Chain chain) throws IOException {
////                                Request original = chain.request();
////
////                                Request.Builder requestBuilder = original.newBuilder()
////                                        .addHeader("Authorization", AUTH)
////                                        .method(original.method(), original.body());
////
////                                Request request = requestBuilder.build();
////                                return chain.proceed(request);
////                            }
////                        }
////                ).build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
//    }
//
//    public static synchronized RetrofitClient getInstance() {
//        if (mInstance == null) {
//            mInstance = new RetrofitClient();
//        }
//        return mInstance;
//    }
    private static Retrofit retrofit = null;

    public RetrofitClient()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://grocerr.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}




