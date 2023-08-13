package com.example.veg.models;

import java.util.Date;

public class SignUpModel {

    public String message;
    public User user;
    public String token;


    public class User {
        public String name;
        public String email;
        public String password_hint;
        public String phone;
        public Object address;
        public Object city;
        public Object pincode;
        public Date updated_at;
        public Date created_at;
        public int id;
    }
}
