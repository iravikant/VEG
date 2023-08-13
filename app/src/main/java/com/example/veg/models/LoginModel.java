package com.example.veg.models;


import java.util.Date;

public class LoginModel {
    public String access_token;
    public String token_type;
    public int expires_in;
    public User user;


    public class User {
        public int id;
        public String name;
        public Object slug;
        public String gender;
        public int age;
        public long phone;
        public String email;
        public String address;
        public String city;
        public Object state;
        public String img;
        public int pincode;
        public Object email_verified_at;
        public String password_hint;
        public Date created_at;
        public Date updated_at;
    }



}
