package com.example.veg.models;

import java.util.Date;
import java.util.List;

public class BestsellerModel {
    public String message;
    public String status;
    public List<bestseller> bestseller;
    public int code;




    public class bestseller {
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
