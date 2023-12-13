package com.example.veg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.veg.Adapter.WishlistAdapter;
import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivityWishListBinding;
import com.example.veg.models.LoginModel;
import com.example.veg.models.WishlistModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListActivity extends AppCompatActivity {
    LoginModel loginModel;
    SessionManager sessionManager;
    int user_id;
    ActivityWishListBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityWishListBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(WishListActivity.this);
        loginModel = sessionManager.getLoginSession();
        addWishlist();
        b.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void addWishlist() {
        Call<WishlistModel> call = RetrofitClient.getInstance().getApi().add("Bearer " + loginModel.access_token, "1");
        call.enqueue(new Callback<WishlistModel>() {
            @Override
            public void onResponse(Call<WishlistModel> call, Response<WishlistModel> response) {
                Log.e("ravi", "" + response.code());
                if (response.isSuccessful()) {
                    Log.e("fffffffffff", new Gson().toJson(response.body()));
                    WishlistAdapter wishlistAdapter = new WishlistAdapter(response.body().data, WishListActivity.this);
                    b.rvWish.setAdapter(wishlistAdapter);
                    if (response.body().data.size() == 0) {
                        Toast.makeText(WishListActivity.this, "Wishlist is empty", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.e("size", "" + response.body().data.size());


                    }
                }


            }

            @Override
            public void onFailure(Call<WishlistModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
}