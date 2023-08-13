package com.example.veg.models;

import java.util.List;

public class BannerModel {
    public String message;
    public String status;
    public List<Banner> banner;
    public int code;

    public class Banner {
        public int id;
        public String name;
        public String slug;
        public String image;
        public String status;
        public String created_at;
        public String updated_at;
        public Object deleted_at;
    }
}
