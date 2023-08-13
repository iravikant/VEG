package com.example.veg.models;

import java.util.List;

public class SearchModel {
    public String message;
    public String status;
    public List<Product> product;
    public int code;

    public class Product {
        public int id;
        public int category_id;
        public String name;
        public String slug;
        public int mrp;
        public int weight;
        public String image;
        public float selling_price;
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
        public String updated_at;
        public Object deleted_at;


    }
}
