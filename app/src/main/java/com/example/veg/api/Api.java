package com.example.veg.api;


import com.example.veg.HomeModel;
import com.example.veg.LoginModel;
import com.example.veg.SignUpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginModel> userData(
            @Field("phone") String phone,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("api/register")
    Call<SignUpModel> signUpData(
            @Field("phone") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("confirm") String confirm,
            @Field("phone") String phone);


    @GET("api/home")
    Call<HomeModel> getHome(
            @Header("Authorization") String Authorization
    );



}
