package com.example.veg;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etPhone, etPassword;
    MaterialButton subBtn;
    String phone = "";
    String password = "";
    TextView tvSignUp;
    GoogleSignInOptions lgn;
    GoogleSignInClient sgn;
    ImageView ggl;
    ActivityLoginBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ggl = findViewById(R.id.ggl);

        lgn = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        sgn = GoogleSignIn.getClient(this, lgn);

        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        subBtn = findViewById(R.id.submit);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });
        ggl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }

        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()) {
                    userData();
                }

            }
        });


    }

    private boolean checkForm() {
        phone = etPhone.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (phone.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
            etPhone.setFocusableInTouchMode(true);
            etPhone.requestFocus();
            return false;

        } else if (phone.length() != 10) {
            Toast.makeText(LoginActivity.this, "phone no should be 10 digit", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;

        } else if (password.length() < 8) {
            Toast.makeText(LoginActivity.this, "password not less than 8 Char", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private void userData() {
        Call<LoginModel> call = RetrofitClient.getInstance().getApi().userData(phone, password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(retrofit2.Call<LoginModel> call, Response<LoginModel> response) {
                Log.e("Token", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("token",response.body().access_token);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void signIn() {
        Intent signInIntent = sgn.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }
}
