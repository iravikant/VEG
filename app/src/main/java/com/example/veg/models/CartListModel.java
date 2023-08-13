package com.example.veg.models;

import java.util.List;

public class CartListModel {


    public String message;
    public String status;
    public List<Cart> cart;
    public int code;
    public String product_base_url;
    public Float subTotal;
    public double totalTax;
    public float total;

    public class Cart {
        public int id;
        public String session_id;
        public int user_id;
        public String product_id;
        public int quantity;
        public Object coupon_id;
        public Object pincode_id;
        public String created_at;
        public String updated_at;
        public Object deleted_at;
        public Product product;
    }

    public class Product {
        public int id;
        public String name;
        public int mrp;
        public Float selling_price;
        public String image;
        public int discount;
        public int gst;
    }
}



