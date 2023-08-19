package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class WishlistModel {
    public String message;
    public String status;
    public List<Wishlist> data;
    public int code;
    public class Wishlist{
        public int id;
        public String session_id;
        public int user_id;
        public String product_id;
        public int quantity;
        public Object coupon_id;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
        public Product product;
    }

    public class Product{
        public int id;
        public int category_id;
        public String name;
        public String slug;
        public int mrp;
        public String weight;
        public String image;
        public Float selling_price;
        public int stock;
        public Object description;
        public Object additional_info;
        public Object sku;
        public Object hsn_code;
        public int discount;
        public int gst;
        public String deal_of_day;
        public String type;
        public String best_seller;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
        public Object vendor_id;
        public String product_unit;
        public String status;
        public int active_status;
    }




}

