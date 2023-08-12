package com.example.veg;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veg.Adapter.HomeAdapter;
import com.example.veg.api.RetrofitClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {
    RecyclerView rec1, rec2, rec3;
    String url = "https://sabjeewala.seomantras.in/api/home";
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rec1 = findViewById(R.id.rec1);
        rec2 = findViewById(R.id.rec2);
        rec3 = findViewById(R.id.rec3);
        accessToken = getIntent().getStringExtra("token");
        getProduct();
        getVeg();
        getFruit();

    }

    private void getFruit() {
        Call<HomeModel> call = RetrofitClient.getInstance().getApi().getHome("Bearer" + accessToken);
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                Log.e("response", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    HomeAdapter adapter = new HomeAdapter(response.body().fruits, HomeActivity.this);
                    rec3.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    private void getVeg() {

        Call<HomeModel> call = RetrofitClient.getInstance().getApi().getHome("Bearer" + accessToken);
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                Log.e("response", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    HomeAdapter adapter = new HomeAdapter(response.body().vegetables, HomeActivity.this);
                    rec2.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void getProduct() {

        Call<HomeModel> call = RetrofitClient.getInstance().getApi().getHome("Bearer " + accessToken);
        call.enqueue(new Callback<HomeModel>() {


            @Override
            public void onResponse(Call<HomeModel> call, retrofit2.Response<HomeModel> response) {
                Log.e("response", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {

                    HomeAdapter adapter = new HomeAdapter(response.body().deals, HomeActivity.this);
                    rec1.setAdapter(adapter);
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