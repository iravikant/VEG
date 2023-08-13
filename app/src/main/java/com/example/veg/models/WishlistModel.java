package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class WishlistModel {
    public String message;
    public String status;
    public List<Wishlist> data;
    public int code;

    public class Wishlist {
        public int id;
        public String session_id;
        public int user_id;
        public int product_id;
        public int quantity;
        public Object coupon_id;
        public Date created_at;
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
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
    }

}

