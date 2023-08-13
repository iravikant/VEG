package com.example.veg.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuccessModel {
    public String message;
    public int code;
    public Order order;
    public List<OrderItem> order_items;

    public class Order {
        public int id;
        public String order_id;
        public String payment_id;
        public String razorpay_orderid;
        public int user_id;
        public int order_price;
        public int payable_price;
        public Object wallet_amount;
        public String status;
        public Object cancel_reson;
        public Object cancel_status;
        public String payment_type;
        public Object delivered_date;
        public Object coupon_id;
        public Object coupon_amount;
        public int delivery_charge;
        public Object captured;
        public String note;
        public String name;
        public String email;
        public String phone;
        public String address;
        public Object state;
        public String city;
        public String pincode;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
        public Object otp;
        public Object vendor_id;

        public Object rEFUNDAMOUNT;
        public Object refund_status;
        public ArrayList<OrderList> order_list;
    }

    public class OrderItem {
        public int id;
        public String order_id;
        public int product_id;
        public int mrp_price;
        public int selling_price;
        public int quantity;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
    }

    public class OrderList {
        public int id;
        public String order_id;
        public int product_id;
        public int mrp_price;
        public int selling_price;
        public int quantity;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
    }


}
