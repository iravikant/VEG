package com.example.veg.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class HomeModel {
    public String message;
    public boolean status;
    public List<Banner> banner;
    public List<Testimonial> testimonial;
    public List<ProductDetails> deals;
    public List<CategoryOfTheProduct> category;
    public List<ProductDetails> vegetables;
    public List<ProductDetails> fruits;
    public List<ProductDetails> spouts;
    public List<ProductDetails> meats;
    public List<ProductDetails> sellers;
    public int code;

    public class Testimonial {
        public int id;
        public String name;
        public String slug;
        public String image;
        public String content;
        public String status;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;

    }
    public class Banner{
        public int id;
        public String name;
        public String slug;
        public String image;
        public String status;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
    }

    public class ProductDetails{
        public int id;
        public int category_id;
        public String name;
        public String slug;
        public int mrp;
        public int weight;
        public String image;
        public String selling_price;
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
        public int vendor_id;
        public String product_unit;
        public int active_status;
    }

    public class CategoryOfTheProduct {
        public int id;
        public String name;
        public String slug;
        public String image;
        public String status;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;
        public Object product;
    }

}
