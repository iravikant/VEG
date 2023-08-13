package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class AboutusModel {
    public String message;
    public String status;
    public List<AboutUs> Aboutus;
    public int code;

    public class AboutUs {
        public int id;
        public String title;
        public String slug;
        public String image;
        public String content;
        public String status;
        public Date created_at;
        public Date updated_at;
        public Object deleted_at;

    }
}
