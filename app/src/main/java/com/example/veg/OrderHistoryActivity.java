package com.example.veg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.veg.Adapter.OrderHistoryAdapter;
import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivityOrderHistoryBinding;
import com.example.veg.models.LoginModel;
import com.example.veg.models.OrderHistoryModel;
import com.example.veg.models.ProfileModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity {
ActivityOrderHistoryBinding b;
    SessionManager sessionManager;
    LoginModel loginModel;
    ProfileModel profileModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(OrderHistoryActivity.this);
        loginModel = sessionManager.getLoginSession();
        profileModel = sessionManager.getUser();
       b =ActivityOrderHistoryBinding.inflate(getLayoutInflater());
       View view = b.getRoot();
       setContentView(view);
       history();
       b.back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onBackPressed();
           }
       });
    }

    private void history() {
        Call<OrderHistoryModel> call= RetrofitClient.getInstance().getApi().history("Bearer" + loginModel.access_token);
        call.enqueue(new Callback<OrderHistoryModel>() {
            @Override
            public void onResponse(Call<OrderHistoryModel> call, Response<OrderHistoryModel> response) {
                if (response.isSuccessful());
                OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(response.body().data, OrderHistoryActivity.this);
                b.rvOrderH.setAdapter(orderHistoryAdapter);

            }

            @Override
            public void onFailure(Call<OrderHistoryModel> call, Throwable t) {

            }
        });
    }
}