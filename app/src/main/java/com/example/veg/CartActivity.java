package com.example.veg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.veg.Adapter.CartAdapter;
import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivityCartBinding;
import com.example.veg.models.AddToCartModel;
import com.example.veg.models.CartListModel;
import com.example.veg.models.DeleteCartModel;
import com.example.veg.models.LoginModel;
import com.example.veg.models.PlaceOrderModel;
import com.example.veg.models.ProfileModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding b;
    SessionManager sessionManager;
    LoginModel loginModel;
    ProfileModel profileModel;
    ImageView ivWishlist;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityCartBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(CartActivity.this);
        loginModel = sessionManager.getLoginSession();
        profileModel = sessionManager.getUser();
        ivWishlist = findViewById(R.id.ivWishlist);


        b.back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        user_id = profileModel.user.id;
        cart();
        ivWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,WishListActivity.class));
            }
        });
        b.ivPriceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,BookingSummaryActivity.class));

            }
        });


        b.placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceOrder();
            }

            private void PlaceOrder() {
                Call<PlaceOrderModel> call = RetrofitClient.getInstance().getApi().placeOrder("Bearer" +loginModel.access_token,"Online","1");
                call.enqueue(new Callback<PlaceOrderModel>() {
                    @Override
                    public void onResponse(Call<PlaceOrderModel> call, Response<PlaceOrderModel> response) {
                        if (response.isSuccessful()) {
                            Log.e("ravi",new Gson().toJson(response.body()));
                            Toast.makeText(CartActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                            SucessFragment dialogueFragment = new SucessFragment();
                            dialogueFragment.show(getSupportFragmentManager(),"fragment_success");


                        }


                    }

                    @Override
                    public void onFailure(Call<PlaceOrderModel> call, Throwable t) {
                        Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    private void cart() {

        Call<CartListModel> call = RetrofitClient.getInstance().getApi().cartList("Bearer " + loginModel.access_token, String.valueOf(user_id));
        call.enqueue(new Callback<CartListModel>() {
            @Override
            public void onResponse(Call<CartListModel> call, Response<CartListModel> response) {
                Log.e("responseCart", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {


                    if (response.body().cart.size() == 0) {
                        Toast.makeText(CartActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
/*
                        b.ivEmpty.setVisibility(View.VISIBLE);
                        b.llCard.setVisibility(View.GONE);*/

                    } else {
                        sessionManager.setCartList(response.body());
                        CartAdapter cartAdapter = new CartAdapter(response.body().cart,CartActivity.this, new RemoveCartListener() {
                            @Override
                            public void onRemove(String product_id) {
                                removeCart(product_id);
                            }
                        }, new CartQuantityListener() {
                            @Override
                            public void onQuantityChanged(String product_id, int value) {
                                changeQuantity(product_id, value);
                            }
                        });
                    b.rvCart.setAdapter(cartAdapter);
                       b.tvTotal.setText(String.valueOf("₹ " + response.body().total));
                        b.tvSubTotal.setText(String.valueOf("₹ " + response.body().subTotal));
                        b.tvTotalTaxes.setText(String.valueOf("₹ " + response.body().totalTax));
                        b.tvTotalView.setText(String.valueOf("₹ " + response.body().total));
//                        b.totalItem.setText(String.valueOf("Subtotal ( " + response.body().cart.get(0).quantity) + " items)");
//
//                       b.address.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent = new Intent(getContext(), PaymentRozerActivity.class);
//                                intent.putExtra("model", new Gson().toJson(response.body()));
//                                getContext().startActivity(intent);
//                            }
//                        });
//
//
//
//                        *//* intent.putExtra("total", String.valueOf("Rs. " + response.body().total));*//*
//                        b.tvDiscount2.setText(String.valueOf("Rs. " + response.body().total));
//
//                        b.ivEmpty.setVisibility(View.GONE);
//                        b.llCard.setVisibility(View.VISIBLE);
                    }

                } else {
                    Toast.makeText(CartActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartListModel> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    void removeCart(String product_id) {
        Call<DeleteCartModel> call = RetrofitClient.getInstance().getApi().delete("Bearer " + loginModel.access_token, String.valueOf(profileModel.user.id), product_id);
        call.enqueue(new Callback<DeleteCartModel>() {
            @Override
            public void onResponse(Call<DeleteCartModel> call, Response<DeleteCartModel> response) {
                Log.e("SushilCartdelete", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    Toast.makeText(CartActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    cart();

                }
            }

            @Override
            public void onFailure(Call<DeleteCartModel> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    void changeQuantity(String product_id, int value) {
        Log.e("cart_quantity", product_id + " " + String.valueOf(value));
        Call<AddToCartModel> call = RetrofitClient.getInstance().getApi().addToCart("Bearer " + loginModel.access_token, String.valueOf(user_id), product_id, value);
        call.enqueue(new Callback<AddToCartModel>() {
            @Override
            public void onResponse(Call<AddToCartModel> call, Response<AddToCartModel> response) {
                Log.e("SushilCart", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    cart();
                }
            }

            @Override
            public void onFailure(Call<AddToCartModel> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}