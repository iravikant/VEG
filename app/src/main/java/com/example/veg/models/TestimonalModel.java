package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class TestimonalModel {
    public String message;
    public String status;
    public List<Testimonial> testimonial;
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
}
