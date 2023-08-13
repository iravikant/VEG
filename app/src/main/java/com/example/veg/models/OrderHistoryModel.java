package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class OrderHistoryModel {
    public String message;
    public String status;
    public List<OrderHistory> data;
    public int code;

    public class OrderHistory {
        public int id;
        public String order_id;
        public Object payment_id;
        public String razorpay_orderid;
        public int user_id;
        public Object product_id;
        public int quantity;
        public int order_price;
        public String payable_price;
        public Object wallet_amount;
        public String status;
        public String payment_type;
        public Object delivered_date;
        public Object coupon_id;
        public Object coupon_amount;
        public Object delivery_charge;
        public Object captured;
        public String note;
        public String name;
        public String email;
        public String phone;
        public String address;
        public String state;
        public String city;
        public String pincode;
        public String created_at;
        public Date updated_at;
        public Object deleted_at;
        public List<Orderlist> orderlist;
    }

    public class Orderlist {
        public int id;
        public String order_id;
        public int product_id;
        public int mrp_price;
        public Float selling_price;
        public int quantity;
        public String created_at;
        public Date updated_at;
        public Object deleted_at;
        public Product product;
    }

    public class Product {
        public int id;
        public int category_id;
        public String name;
        public String slug;
        public int mrp;
        public int weight;
        public String image;
        public Float selling_price;
        public int stock;
        public String description;
        public String additional_info;
        public int sku;
        public int hsn_code;
        public int discount;
        public int gst;
        public String deal_of_day;
        public String type;
        public String best_seller;
        public String status;
        public String created_at;
        public Date updated_at;
        public Object deleted_at;
    }


}