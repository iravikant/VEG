package com.example.veg.api;


import com.example.veg.models.AddToCartModel;
import com.example.veg.models.BannerModel;
import com.example.veg.models.CartListModel;
import com.example.veg.models.DeleteCartModel;
import com.example.veg.models.HomeModel;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.example.veg.models.SignUpModel;
import com.example.veg.models.WishlistModel;

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

    @GET("api/banners")
    Call<BannerModel> banner(
            @Header("Authorization") String auth
    );
    @GET("api/home")
    Call<HomeModel> getHome(
            @Header("Authorization") String Authorization
    );
    @FormUrlEncoded
    @POST("api/add-to-cart")
    Call<AddToCartModel> addToCart(
            @Header("Authorization") String auth,
            @Field("user_id") String user_id,
            @Field("product_id") String product_id,
            @Field("quantity") int quantity
    );
    @FormUrlEncoded
    @POST("api/cart-list")
    Call<CartListModel> cartList(
            @Header("Authorization") String auth,
            @Field("user_id") String user_id
    );
    @GET("api/loggeduser")
    Call<ProfileModel> myProfile(
            @Header("Authorization") String auth
    );
    @FormUrlEncoded
    @POST("api/remove-from-cart")
    Call<DeleteCartModel> delete(
            @Header("Authorization") String auth,
            @Field("user_id") String user_id,
            @Field("product_id") String product_id
    );
@FormUrlEncoded
    @POST("api/wishlist")
    Call<WishlistModel> add(
        @Header("Authorization") String auth,
        @Field("user_id") String user_id
);

}
