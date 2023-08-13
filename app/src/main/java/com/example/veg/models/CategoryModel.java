package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class CategoryModel {
    public String message;
    public String status;
    public List<category> category;
    public int code;

    public class category {
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
