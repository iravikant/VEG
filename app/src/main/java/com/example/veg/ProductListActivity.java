package com.example.veg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veg.Adapter.BestsellerAdapter;
import com.example.veg.Adapter.CategoryAdapter;
import com.example.veg.Adapter.FreshVegetableAdapter;
import com.example.veg.Adapter.FreshmeatAdapter;
import com.example.veg.Adapter.FruitlistAdapter;
import com.example.veg.Adapter.HomeAdapter;
import com.example.veg.Adapter.SpoutrsAdapter;
import com.example.veg.api.RetrofitClient;
import com.example.veg.models.HomeModel;
import com.example.veg.models.LoginModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView rvHorizontal, rvList, rvList3;
    LinearLayout llSearch;
    SessionManager sessionManager;
    LoginModel sessionModel;
    Intent intent;
    Context context;
    ImageView orderHistory, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        context = ProductListActivity.this;
        sessionManager = new SessionManager(ProductListActivity.this);
        sessionModel = sessionManager.getLoginSession();
        intent = getIntent();
       /* subcategory();
        product();*/

        rvHorizontal = findViewById(R.id.rvHorizontal);
        rvList = findViewById(R.id.rvList);
        rvList3 = findViewById(R.id.rvList3);
        llSearch = findViewById(R.id.llSearch);
        back = findViewById(R.id.back);
        orderHistory = findViewById(R.id.orderHistory);
        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
       /* llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });*/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onBackPressed();
            }
        });

        parseData();
    }

    private void parseData() {
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString(Constants.TYPE);
        Call<HomeModel> call = RetrofitClient.getInstance().getApi().getHome("Bearer " + sessionModel.access_token);
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                Log.e("response", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (type.equals(Constants.CATEGORY)) {
                        CategoryAdapter adapter = new CategoryAdapter(response.body().category, ProductListActivity.this);
                        rvList3.setAdapter(adapter);
                    }
                    if (type.equals(Constants.DEAL)) {
                        HomeAdapter adapter = new HomeAdapter(response.body().deals, ProductListActivity.this);
                        rvList3.setAdapter(adapter);
                    }
                    if (type.equals(Constants.VEGETABLES)) {
                        FreshVegetableAdapter adapter = new FreshVegetableAdapter(response.body().vegetables, ProductListActivity.this);
                        rvList.setAdapter(adapter);
                    }
                    if (type.equals(Constants.FRUITS)) {
                        FruitlistAdapter adapter = new FruitlistAdapter(response.body().fruits, ProductListActivity.this);
                        rvList3.setAdapter(adapter);
                    }
                    if (type.equals(Constants.SPOUTS)) {
                        SpoutrsAdapter adapter = new SpoutrsAdapter(response.body().spouts, ProductListActivity.this);
                        rvList.setAdapter(adapter);
                    }
                    if (type.equals(Constants.MEATS)) {
                        FreshmeatAdapter adapter = new FreshmeatAdapter(response.body().meats, ProductListActivity.this);
                        rvList.setAdapter(adapter);
                    }
                    if (type.equals(Constants.SELLERS)) {
                        BestsellerAdapter adapter = new BestsellerAdapter(response.body().sellers, ProductListActivity.this);
                        rvList3.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

 /*   private void subcategory() {
        Call<SubcategoryModel> call = RetrofitClient.getInstance().getApi().sub_category("Bearer " + sessionModel.token);
        call.enqueue(new Callback<SubcategoryModel>() {
            @Override
            public void onResponse(Call<SubcategoryModel> call, Response<SubcategoryModel> response) {
                Log.e("subcategory", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {

                    SmallProductAdapter subcategoryAdapter = new SmallProductAdapter(context,response.body().getSub_category());

                    fruitRecycleview.setAdapter(subcategoryAdapter);
                    LinearLayoutManager layoutManager1 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
                    fruitRecycleview.setLayoutManager(layoutManager1);
                }
            }

            @Override
            public void onFailure(Call<SubcategoryModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    private void product() {
        Call<ProductModel> call = RetrofitClient.getInstance().getApi().product("Bearer " + sessionModel.token);
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                Log.e("subcategory", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {

                    ProductListAdapter productListAdapter1 = new ProductListAdapter(context,response.body().product);

                    vaegitablesRecycleview.setAdapter(productListAdapter1);
                    LinearLayoutManager layoutManager1 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
                    vaegitablesRecycleview.setLayoutManager(layoutManager1);







                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }*/

}