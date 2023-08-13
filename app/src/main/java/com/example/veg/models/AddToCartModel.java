package com.example.veg.models;

public class AddToCartModel {
    public String message;
    public String status;
    public Cart cart;
    public int code;
    public class Cart{
        public String session_id;
        public int user_id;
        public String product_id;
        public Object quantity;
        public String updated_at;
        public String created_at;
        public int id;
    }
}
