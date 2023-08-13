package com.example.veg.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ProductModel {
    public String message;
    public String status;
    public List<product> product;

    public int code;

    public List<Bestselle> getBestselle() {
        return bestselle;
    }

    public void setBestselle(List<Bestselle> bestselle) {
        this.bestselle = bestselle;
    }

    @SerializedName("best seller")
    @Expose
    private List<Bestselle> bestselle = null;


    public class product {
        public int id;
        public String mrp;
        public int categories_id;
        public String weight;
        public String selling_price;
        public String name;
        public String slug;
        public String image;
        public String status;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;

    }

    public class Bestselle {
        public int id;
        public String mrp;
        public int categories_id;
        public String weight;
        public String selling_price;
        public String name;
        public String slug;
        public String image;
        public String status;
        public String best_seller;
        public String featured;
        public String type;
        public String description;
        public String additional_info;

    }

}
