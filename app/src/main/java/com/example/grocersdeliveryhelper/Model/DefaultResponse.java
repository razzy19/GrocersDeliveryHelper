package com.example.grocersdeliveryhelper.Model;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("state")
    private boolean state;

    @SerializedName("message")
    private String msg;

    public DefaultResponse(boolean state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public boolean isErr() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isState() {
        return state;

    }
}
