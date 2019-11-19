package com.example.grocersdeliveryhelper.Model;

public class CompletedOrdersModel {
    String packageID;
    String OrderID;
    String custName;
    String custPhone;
    String custAddress;
    String custAmount;
    String date;
    String time;

    public CompletedOrdersModel(String packageID, String orderID, String custName, String custPhone, String custAddress, String custAmount, String date,String time) {
        this.packageID = packageID;
        OrderID = orderID;
        this.custName = custName;
        this.custPhone = custPhone;
        this.custAddress = custAddress;
        this.custAmount = custAmount;
        this.date = date;
        this.time= time;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public String getCustName() {
        return custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public String getCustAmount() {
        return custAmount;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }


    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public void setCustAmount(String custAmount) {
        this.custAmount = custAmount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

