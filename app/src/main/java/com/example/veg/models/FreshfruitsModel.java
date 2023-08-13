package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class FreshfruitsModel {
    public String message;
    public String status;
    public List<fruits> fruits;
    public int code;




    public class fruits {
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


}
