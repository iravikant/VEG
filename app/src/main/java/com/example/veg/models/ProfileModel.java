package com.example.veg.models;

public class ProfileModel {
    public User user;
    public String message;
    public String status;


    public class User {
        public int id;
        public String name;
        public String slug;
        public String gender;
        public String age;
        public String state;
        public String phone;
        public String email;
        public String address;
        public String city;
        public String img;
        public String pincode;
        public Object email_verified_at;
        public String password_hint;
        public String created_at;
        public String updated_at;
    }
}
