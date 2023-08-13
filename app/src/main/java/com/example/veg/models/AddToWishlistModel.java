package com.example.veg.models;

public class AddToWishlistModel {
    public String message;
    public String status;
    public Data data;
    public int code;

    public class Data{
        public int id;
        public String session_id;
        public int user_id;
        public int product_id;
        public int quantity;
        public Object coupon_id;
        public String created_at;
        public String updated_at;
        public Object deleted_at;
    }
}
