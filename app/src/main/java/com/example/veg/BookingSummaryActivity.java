package com.example.veg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivityBookingSummaryBinding;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.example.veg.models.SuccessModel;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingSummaryActivity extends AppCompatActivity {
TextView tvSubTotal2,tvDelivery2,tvTotalTaxes2,tvDiscount2,tvTotal2,tvTotalView2;
MaterialButton btnMakePay;

    SessionManager sessionManager;
    LoginModel loginModel;
    ProfileModel profileModel;
    ActivityBookingSummaryBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);
        sessionManager = new SessionManager(BookingSummaryActivity.this);
        loginModel = sessionManager.getLoginSession();
        profileModel = sessionManager.getUser();
        tvSubTotal2 = findViewById(R.id.tvSubTotal2);
        tvTotalTaxes2 =findViewById(R.id.tvTotalTaxes2);
        tvDelivery2 =findViewById(R.id.tvDelivery2);
        tvTotal2 =findViewById(R.id.tvTotal2);
        tvTotalView2 =findViewById(R.id.tvTotalView2);
        tvDiscount2 =findViewById(R.id.tvDiscount2);
        btnMakePay =findViewById(R.id.btnMakePay);
        

        btnMakePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakePayment();
            }

            private void MakePayment() {
                Call <SuccessModel> call = RetrofitClient.getInstance().getApi().success("Bearer" + loginModel.access_token);
                call.enqueue(new Callback<SuccessModel>() {
                    @Override
                    public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {

                    }

                    @Override
                    public void onFailure(Call<SuccessModel> call, Throwable t) {

                    }
                });
            }
        });

    }

}