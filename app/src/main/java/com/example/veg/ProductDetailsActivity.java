package com.example.veg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.veg.Adapter.RelatedProductAdapter;
import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivityProductDetailsBinding;
import com.example.veg.models.AddToCartModel;
import com.example.veg.models.AddToWishlistModel;
import com.example.veg.models.HomeModel;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.example.veg.models.SingleProductModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
   /*
    ActivityProductDetailsBinding b;
    SessionManager sessionManager;
  LoginModel loginModel;
    ProfileModel profileModel;
    Context context;
    int quantity = 0;
    String product_id;
    int user_id = 0;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(ProductDetailsActivity.this);
        loginModel = sessionManager.getLoginSession();
        profileModel = sessionManager.getUser();
        Intent intent = getIntent();
        Log.e("sushilid", new Gson().toJson(sessionManager.getUser()));
        user_id = profileModel.user.id;

        String model = intent.getStringExtra("model");

        HomeModel.ProductDetails productDetails = new Gson().fromJson(model, HomeModel.ProductDetails.class);

        if (productDetails.image != null && !productDetails.image.isEmpty()) {
//            Glide.with(ProductDetailsActivity.this).load(productDetails.image).into(b.ivImage);
        }
        b.tvName.setText(productDetails.name);
        b.tvDiscount.setText(String.valueOf("Rs." + (productDetails.mrp)));
        b.tvPrice.setText(String.valueOf("Rs." + (productDetails.selling_price)+" /"+productDetails.product_unit));
        b.mtTitle.setTitle(productDetails.name);
        if (productDetails.description==null){
            b.tvDescription.setText("Description not Available");
        }else{
            b.tvDescription.setText(Html.fromHtml(productDetails.description));}
        if (productDetails.additional_info==null){
            b.text5.setText("");
        }else{
            b.text5.setText(Html.fromHtml(productDetails.additional_info));
        }

        b.tvType.setText(productDetails.status);
        product_id = String.valueOf(productDetails.id);
        user_id = profileModel.user.id;
        name = productDetails.name;

        b.nsStepperAdult.setOnValueChangedListener(new NumberStepper.OnValueChangedListener() {
            @Override
            public void onValueChanged(NumberStepper view, int value) {
                quantity = value;
            }
        });

        b.mtTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        b.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
        b.tvAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToWishlist();
            }
        });
        getRelatedProduct();
    }

    private void getRelatedProduct() {
        Log.e("name", name);
        Log.e("SushilRelated", "Bearer " + loginModel.access_token);
        Call<SingleProductModel> call = RetrofitClient.getInstance().getApi().getSingle("Bearer " + loginModel.access_token, name);
        call.enqueue(new Callback<SingleProductModel>() {
            @Override
            public void onResponse(Call<SingleProductModel> call, Response<SingleProductModel> response) {
                Log.e("SushilRelated", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    // Toast.makeText(ProductDetailsActivity.this, "success", Toast.LENGTH_SHORT).show();
                    RelatedProductAdapter relatedProductAdapter = new RelatedProductAdapter(response.body().Related_Product, ProductDetailsActivity.this);
                    b.vaegitablesRecycleview.setAdapter(relatedProductAdapter);
                }
            }

            @Override
            public void onFailure(Call<SingleProductModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    private void addToCart() {
        Call<AddToCartModel> call = RetrofitClient.getInstance().getApi().addToCart("Bearer " + loginModel.access_token, String.valueOf(user_id), product_id, quantity);
        call.enqueue(new Callback<AddToCartModel>() {
            @Override
            public void onResponse(Call<AddToCartModel> call, Response<AddToCartModel> response) {
                Log.e("SushilCart", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    Toast.makeText(ProductDetailsActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCartModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void addToWishlist() {
        Call<AddToWishlistModel> call = RetrofitClient.getInstance().getApi().addToWishlist("Bearer " + loginModel.access_token, String.valueOf(user_id), product_id,1);
        call.enqueue(new Callback<AddToWishlistModel>() {
            @Override
            public void onResponse(Call<AddToWishlistModel> call, Response<AddToWishlistModel> response) {
                Log.e("SushilCart", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    Toast.makeText(ProductDetailsActivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddToWishlistModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }*/
}
