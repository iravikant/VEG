package com.example.veg.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class SubcategoryModel {
    public String message;
    public String status;

    public int code;

    public List<SubcategoryModel.sub_category> getSub_category() {
        return sub_category;
    }

    public void setSub_category(List<SubcategoryModel.sub_category> sub_category) {
        this.sub_category = sub_category;
    }

    @SerializedName("sub-category")
    @Expose
    private List<sub_category> sub_category = null;

    public class sub_category {
        public int id;
        public String name;
        public String slug;
        public String image;
        public String status;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;

    }
}
