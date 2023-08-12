package com.example.veg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.veg.api.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegistrationActivity extends AppCompatActivity {
TextInputEditText editTextName,editTextNumber,editTextEmail,editTextPassword,editTextConfirm;
MaterialButton subBtn;
    String name = "";
    String phone = "";
    String email = "";
    String password = "";
    String confirm = "";
    String url = "https://sabjeewala.seomantras.in/api/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextName = findViewById(R.id.edtName);
        editTextEmail = findViewById(R.id.edtEmail);
        editTextPassword = findViewById(R.id.etPassword);
        editTextConfirm = findViewById(R.id.etConfirm);
        editTextNumber = findViewById(R.id.etContact);

        subBtn = findViewById(R.id.submit);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()) {
                    signUpData();
                }

            }
        });


    }

    private boolean checkForm() {
       name = editTextName.getText().toString().trim();
        phone = editTextNumber.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        confirm = editTextConfirm.getText().toString().trim();



        return true;
    }


    void signUpData() {
        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, null, "Processing...", false, false);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("message");
                    if (status.equalsIgnoreCase("User successfully registered")) {
                        JSONObject jsonObj = new JSONObject(jsonObject.getString("user"));

                        Gson gson = new Gson();
                       SignUpModel sessionModel = gson.fromJson(String.valueOf(jsonObj), SignUpModel.class);
                       // sessionManager.createLoginSession(sessionModel);
                        Toast.makeText(RegistrationActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(RegistrationActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegistrationActivity.this, "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(RegistrationActivity.this, " Please try again.", Toast.LENGTH_SHORT).show();
            }


        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("phone", phone);
                params.put("password", password);
                params.put("confirm", confirm);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.myGetMySingleton(RegistrationActivity.this).myAddToRequest(stringRequest);
    }
}
