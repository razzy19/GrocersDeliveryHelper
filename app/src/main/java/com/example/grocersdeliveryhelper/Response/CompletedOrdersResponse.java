package com.example.grocersdeliveryhelper.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CompletedOrdersResponse {

    @SerializedName("state")
    String state;

    @SerializedName("data")
    ArrayList<CompletedOrdersResponse.CompletedOrder> pendingOrders;

    public CompletedOrdersResponse(String state, ArrayList<CompletedOrdersResponse.CompletedOrder> pendingOrders) {
        this.state = state;
        this.pendingOrders = pendingOrders;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<CompletedOrdersResponse.CompletedOrder> getCompletedOrders() {
        return pendingOrders;
    }

    public void setCompletedOrders(ArrayList<CompletedOrdersResponse.CompletedOrder> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public static class CompletedOrder{

        @SerializedName("id")
        int id;

        @SerializedName("contact")
        String contact;

        @SerializedName("fullname")
        String fullname;

        @SerializedName("status")
        String status;

        @SerializedName("payment_mode")
        String payment_mode;

        @SerializedName("scheduled_delivery_date")
        String date;

        @SerializedName("total")
        int total;

        @SerializedName("scheduled_delivery_time")
        String time;

        @SerializedName("delivery_address")
        String address;

        @SerializedName("orders")
        ArrayList<CompletedOrdersResponse.CompletedOrder.Orders> orders;

        public CompletedOrder(int id,String time, String contact, String fullname, String status, String payment_mode, String date, int total,String address, ArrayList<CompletedOrdersResponse.CompletedOrder.Orders> orders) {
            this.id = id;
            this.time= time;
            this.contact = contact;
            this.fullname = fullname;
            this.status = status;
            this.payment_mode = payment_mode;
            this.date = date;
            this.total = total;
            this.orders = orders;
            this.address = address;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayment_mode() {
            return payment_mode;
        }

        public void setPayment_mode(String payment_mode) {
            this.payment_mode = payment_mode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<CompletedOrdersResponse.CompletedOrder.Orders> getOrders() {
            return orders;
        }

        public void setOrders(ArrayList<CompletedOrdersResponse.CompletedOrder.Orders> orders) {
            this.orders = orders;
        }

        public static class Orders{

            @SerializedName("qty")
            int qty;

            @SerializedName("unit_price")
            int unit_price;

            @SerializedName("name")
            String name;

            public Orders(int qty, int unit_price, String name) {
                this.qty = qty;
                this.unit_price = unit_price;
                this.name = name;
            }

            public int getQty() {
                return qty;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }

            public int getUnit_price() {
                return unit_price;
            }

            public void setUnit_price(int unit_price) {
                this.unit_price = unit_price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

    }
}
