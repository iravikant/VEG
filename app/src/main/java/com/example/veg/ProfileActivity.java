package com.example.veg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.veg.api.RetrofitClient;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
ImageView ivProfile;
TextView tvName;
MaterialButton mbEdit;
SessionManager sessionManager;
LoginModel loginModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sessionManager=new SessionManager(ProfileActivity.this);
        loginModel= sessionManager.getLoginSession();
        tvName = findViewById(R.id.tvName);
        ivProfile = findViewById(R.id.ivProfile);
        mbEdit = findViewById(R.id.mbEdit);
              getProfile();
    }
    private void getProfile() {


        Call<ProfileModel> call = RetrofitClient.getInstance().getApi().myProfile("Bearer " + loginModel.access_token);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                Log.e("SushilCart", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    sessionManager.setUserDetails(response.body());
                    tvName.setText(response.body().user.name);

                    Glide.with(ProfileActivity.this).load(response.body().user.img).into(ivProfile);
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}