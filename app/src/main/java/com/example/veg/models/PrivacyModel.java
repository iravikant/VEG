package com.example.veg.models;

import java.util.List;

public class PrivacyModel {
    public String message;
    public String status;
    public List<Datum> data;
    public int code;

    public class Datum {
        public String name;
        public String content;
    }
}
