package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class SingleProductModel {
    public String message;
    public String status;
    public Object single_Product;
    public List<RelatedProduct> Related_Product;
    public int code;

    public class Category {
        public int id;
        public String name;
        public String slug;
        public String image;
        public String status;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
        public Object vendor_id;
    }

    public class RelatedProduct {
        public int id;
        public int category_id;
        public String name;
        public String slug;
        public int mrp;
        public int weight;
        public String image;
        public double selling_price;
        public int stock;
        public String description;
        public String additional_info;
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
        public int vendor_id;
        public String product_unit;
        public String status;
        public int active_status;
        public Category category;
    }

    public class Root {
        public String message;
        public String status;
        public Object single_Product;
        public List<RelatedProduct> related_Product;
        public int code;
    }
}
