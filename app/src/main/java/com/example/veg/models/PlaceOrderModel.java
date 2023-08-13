package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class PlaceOrderModel {
    public String message;
    public int code;
    public Order order;
    public List<OrderItem> order_items;

    public class Order {
        public String order_id;
        public int user_id;
        public String payment_id;
        public int order_price;
        public String delivery_charge;
        public int payable_price;
        public String status;
        public String payment_type;
        public String name;
        public String email;
        public long phone;
        public String city;
        public int pincode;
        public String address;
        public String note;
        public Date updated_at;
        public Date created_at;
        public String id;
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

}
