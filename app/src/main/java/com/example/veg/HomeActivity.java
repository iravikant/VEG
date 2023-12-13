package com.example.veg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veg.Adapter.BannerAdapter;
import com.example.veg.Adapter.BestsellerAdapter;
import com.example.veg.Adapter.CategoryAdapter;
import com.example.veg.Adapter.FreshVegetableAdapter;
import com.example.veg.Adapter.FreshmeatAdapter;
import com.example.veg.Adapter.FruitlistAdapter;
import com.example.veg.Adapter.HomeAdapter;
import com.example.veg.Adapter.SpoutrsAdapter;
import com.example.veg.Adapter.TestmonailAdapter;
import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivityHomeBinding;
import com.example.veg.models.BannerModel;
import com.example.veg.models.HomeModel;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {
    RecyclerView rvDeal, rvCategory, rvFresh, rvOurBlog;
    String url = "https://sabjeewala.seomantras.in/api/home";
    String accessToken;
    SessionManager sessionManager;
    LoginModel loginModel;
    TextView searchText;
    int user_id;
    ImageView ivCart;
    ActivityHomeBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManager = new SessionManager(HomeActivity.this);
        loginModel = sessionManager.getLoginSession();


        b.cartHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
            }
        });
        b.navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });


    getProfile();
        home();
        banner();
    }

    private void banner() {
        Call<BannerModel> call = RetrofitClient.getInstance().getApi().banner("Bearer " + loginModel.access_token);
        call.enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                Log.e("banner response", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {

                    BannerAdapter bannerAdapter = new BannerAdapter(response.body().banner, HomeActivity.this);
                    b.imageSlider.setSliderAdapter(bannerAdapter);

                }
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                HomeActivity.this.startActivity(intent);
                t.printStackTrace();
            }
        });
    }

    private void getProfile() {


        Call<ProfileModel> call = RetrofitClient.getInstance().getApi().myProfile("Bearer " + loginModel.access_token);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                Log.e("SushilCart", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    sessionManager.setUserDetails(response.body());
                    //  Toast.makeText(getContext(), "user details", Toast.LENGTH_SHORT).show();
                    user_id = response.body().user.id;
                    //  b.searchText.setText(response.body().user.name);
//                     pincode = response.body().user.pincode;
//                   cart();
//                    Glide.with(getContext()).load(response.body().user.img).into(b.ivImage);
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void home() {
        Call<HomeModel> call = RetrofitClient.getInstance().getApi().getHome("Bearer " + loginModel.access_token);
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                Log.e("responsesushil", "" + new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (response.body().code == 400) {
                        b.ok.setVisibility(View.VISIBLE);
                        b.okkf.setVisibility(View.GONE);
                        Toast.makeText(HomeActivity.this, response.body().message, Toast.LENGTH_SHORT).show();

                    } else {
                        b.ok.setVisibility(View.GONE);
                        b.okkf.setVisibility(View.VISIBLE);
                        // cart();
                        HomeAdapter homeAdapter = new HomeAdapter(response.body().deals, HomeActivity.this);
                        b.rvDeal.setAdapter(homeAdapter);
                        CategoryAdapter categoryAdapter = new CategoryAdapter(response.body().category, HomeActivity.this);
                        b.rvCategory.setAdapter(categoryAdapter);
                        FreshVegetableAdapter freshvegitablesAdapter = new FreshVegetableAdapter(response.body().vegetables, HomeActivity.this);
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        b.rvFresh.setLayoutManager(layoutManager1);
                        b.rvFresh.setAdapter(freshvegitablesAdapter);
                        FruitlistAdapter fruitAdapter = new FruitlistAdapter(response.body().fruits, HomeActivity.this);
                        LinearLayoutManager layoutManager2 = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        b.rvPic.setLayoutManager(layoutManager2);
                        b.rvPic.setAdapter(fruitAdapter);
                        SpoutrsAdapter sproutAdapter = new SpoutrsAdapter(response.body().spouts, HomeActivity.this);
                        LinearLayoutManager layoutManager3 = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        b.rvSprout.setLayoutManager(layoutManager3);
                        b.rvSprout.setAdapter(sproutAdapter);
                        FreshmeatAdapter freshmeatAdapter = new FreshmeatAdapter(response.body().meats, HomeActivity.this);
                        LinearLayoutManager layoutManager4 = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        b.freshmeatRecycleview.setLayoutManager(layoutManager4);
                        b.freshmeatRecycleview.setAdapter(freshmeatAdapter);
                        BestsellerAdapter bestsellerAdapter = new BestsellerAdapter(response.body().sellers, HomeActivity.this);
                        LinearLayoutManager layoutManager5 = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        b.bestselerRecycleview.setLayoutManager(layoutManager5);
                        b.bestselerRecycleview.setAdapter(bestsellerAdapter);
                        TestmonailAdapter adapter = new TestmonailAdapter(response.body().testimonial, HomeActivity.this);
                        LinearLayoutManager layoutManager6 = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        b.testmonialRecycleview.setLayoutManager(layoutManager6);
                        b.testmonialRecycleview.setAdapter(adapter);
                    }
                }


            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

}