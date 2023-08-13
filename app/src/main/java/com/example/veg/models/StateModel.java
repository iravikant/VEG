package com.example.veg.models;

import java.util.List;

public class StateModel {
    public String message;
    public String status;
    public List<State> data;
    public int code;

    public class State {
        public String name;
        public int id;

    }
}
