package com.example.veg;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivitySuccessBinding;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.example.veg.models.SuccessModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessActivity extends AppCompatActivity {
    ActivitySuccessBinding b;
    SessionManager sessionManager;
    LoginModel loginModel;
    ProfileModel profileModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(SuccessActivity.this);
        loginModel = sessionManager.getLoginSession();
        profileModel = sessionManager.getUser();
        b = ActivitySuccessBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        getSuccess();
    }

    private void getSuccess() {
        Call<SuccessModel> call = RetrofitClient.getInstance().getApi().success("Bearer" + loginModel.access_token);
        call.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {
                if (response.isSuccessful()) ;
                Toast.makeText(SuccessActivity.this, "okkkkk", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {

            }
        });

    }
}