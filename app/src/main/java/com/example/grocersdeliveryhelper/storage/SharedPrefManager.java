package com.example.grocersdeliveryhelper.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;




public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    public SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveAddress(String address){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.d("SharedPrefsave", address);
        editor.putString("address", address).apply();


    }

    public String retrieveAddress(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String address= sharedPreferences.getString("address",null);
        Log.d("SharedPrefget", address+"");
        return address;

    }

    public void saveUserData(String name,String contact)
    {
        String encryptedName = "";
        String encryptedContact  = "";

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try
        {
            encryptedName = AESUtils.encrypt(name);
            encryptedContact = AESUtils.encrypt(contact);
            Log.d("encryptedName", encryptedName);
            Log.d("encryptedContact", encryptedContact);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        editor.putString("name",encryptedName).apply();
        editor.putString("contact",encryptedContact).apply();
    }


    public void saveToken(String token)
    {

        String encryptedKey = "";
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try
        {
            encryptedKey = AESUtils.encrypt(token);
            Log.d("encryptedkey", encryptedKey);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        editor.putString("token", encryptedKey).apply();

    }

    public String getToken() {

        String decryptedKey = "";
        Log.d("inhere", "inhere");

         SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String token= sharedPreferences.getString("token",null);
        try
        {
            decryptedKey = AESUtils.decrypt(token);
            Log.d("decryptedkey", token);

        }
        catch (Exception e)
        {
            e.printStackTrace();
    }
        return decryptedKey;

    }

    public String getEncryptedName(){
        String decryptedKey = "";
        Log.d("inhere", "inhere");

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String token= sharedPreferences.getString("name",null);
        try
        {
            decryptedKey = AESUtils.decrypt(token);
            Log.d("decryptedkey", token);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return decryptedKey;
    }

    public String getEncryptedContact(){
        String decryptedKey = "";
        Log.d("inhere", "inhere");

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String token= sharedPreferences.getString("contact",null);
        try
        {
            decryptedKey = AESUtils.decrypt(token);
            Log.d("decryptedkey", token);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return decryptedKey;
    }


    public String isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null) ;
        return  token;
    }


    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
