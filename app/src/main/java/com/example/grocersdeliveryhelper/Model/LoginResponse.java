package com.example.grocersdeliveryhelper.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("state")
    private String state;

    @SerializedName("token")
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String state, String token) {
        this.state = state;
        this.token = token;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
