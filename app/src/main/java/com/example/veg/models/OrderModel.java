package com.example.veg.models;

public class OrderModel {
    public String message;
    public int code;
    public Order data;

    public class Order{
        public int id;
        public String order_id;
        public Object payment_id;
        public String razorpay_orderid;
        public int user_id;
        public Object product_id;
        public int quantity;
        public double order_price;
        public String payable_price;
        public Object wallet_amount;
        public Object status;
        public Object payment_type;
        public Object delivered_date;
        public Object coupon_id;
        public Object coupon_amount;
        public Object delivery_charge;
        public Object captured;
        public Object note;
        public Object name;
        public Object email;
        public Object phone;
        public Object address;
        public Object state;
        public Object city;
        public Object pincode;
        public String created_at;
        public String updated_at;
        public Object deleted_at;
    }

}
