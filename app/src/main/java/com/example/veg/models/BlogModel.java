package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class BlogModel {
    public String message;
    public String status;
    public List<Blog> blog;
    public int code;

    public class Blog {
        public int id;
        public String title;
        public String slug;
        public String image;
        public String content;
        public String description;
        public String created_at;
        public Date updated_at;
        public Object deleted_at;
    }
}
