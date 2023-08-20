package com.example.veg;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Header;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.veg.databinding.ActivityEditProfileBinding;
import com.example.veg.models.LoginModel;
import com.example.veg.models.SignUpModel;
import com.google.gson.Gson;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class EditProfileActivity extends AppCompatActivity implements PickiTCallbacks {
    ActivityEditProfileBinding b;
    SessionManager sessionManager;
    LoginModel sessionModel;
    Intent intent;
    String pickType;
    int state_id;
    RadioGroup radioGroup;
    RadioButton rdMale, rdFemale;
    String state = "";
    String gender = "";
    String age = "";
    int id = 0;
    Spinner spinner;
    String auth;
    String profileImagePath = "";
    String emailText = "";
    String mobileText = "";
    String nameText = "";
    String addressText = "";
    String cityText = "";
    String pinText = "";
    LoginModel loginModel;

    PickiT pickiT;
    String category_id;
    int PERMISSION_CODE = 193;
    String URL = "https://sabjeewala.seomantras.in/api/update-profile/1";
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);

                    pickiT.getPath(uri, Build.VERSION.SDK_INT);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });
    private String[] permissions = {android.Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityEditProfileBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);

        sessionManager = new SessionManager(EditProfileActivity.this);
        loginModel = sessionManager.getLoginSession();
        auth = "Bearer " + loginModel.access_token;

        pickiT = new PickiT(this, this, this);
        b.back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ClickListener();

        b.submit.setOnClickListener(v -> {
            if (checkForm()) {
                if (profileImagePath.isEmpty()) {
                     updateProfileWithoutImage();
                } else {
                    profileUpdate();
                }
            } else {
                Toast.makeText(EditProfileActivity.this, "Please fill the form", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void updateProfileWithoutImage() {
        final ProgressDialog progressDialog = ProgressDialog.show(EditProfileActivity.this, null, "Processing...", false, false);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equalsIgnoreCase("success")) {
                        JSONObject jsonObj = new JSONObject(jsonObject.getString("user"));

                        Gson gson = new Gson();
                       SignUpModel signUpModel = gson.fromJson(String.valueOf(jsonObj), SignUpModel.class);
                      //  sessionManager.createLoginSession(sessionModel);
                        Log.e("response", ""+signUpModel);

                        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(EditProfileActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(EditProfileActivity.this, "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(EditProfileActivity.this, " Please try again.", Toast.LENGTH_SHORT).show();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, responseListener, errorListener) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", auth);
                return params;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", emailText);
                params.put("phone", mobileText);
                params.put("name", nameText);
                params.put("age", age);
                params.put("gender", gender);
                params.put("address", addressText);
                params.put("pincode", pinText);
                params.put("user_id", "1");

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.myGetMySingleton(EditProfileActivity.this).myAddToRequest(stringRequest);
    }



    void profileUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> params = new HashMap<>();
                params.put("email", emailText);
                params.put("phone", mobileText);
                params.put("name", nameText);
                params.put("age",age);
                params.put("gender", gender);
                params.put("address", addressText);
                params.put("pincode", pinText);
                params.put("user_id", 1);


                Log.e("sushil", "upload start: " + profileImagePath);
                String result = multipartRequest(URL, params, profileImagePath, "image", "image/jpeg");
                Log.e("sushil", result);


            }
        }).start();
    }


    private boolean checkForm() {
        nameText = b.etName.getText().toString().trim();
        mobileText = b.etMobile.getText().toString().trim();
        emailText = b.etEmail.getText().toString().trim();
        gender = b.etGender.getText().toString().trim();

        addressText = b.etAddress.getText().toString().trim();
       age = b.etAge.getText().toString().trim();
        pinText = b.pinCode.getText().toString().trim();


      /*  if (nameText.isEmpty()) {
            b.etName.setError("Name is required");
            return false;
        }
        if (mobileText.isEmpty()) {
            Toast.makeText(this, "Enter mobile number", Toast.LENGTH_SHORT).show();
            b.etMobile.setFocusableInTouchMode(true);
            b.etMobile.requestFocus();
            return false;
        } else if (!Utils.myMobileValid(mobileText)) {
            Toast.makeText(this, "Invalid mobile number", Toast.LENGTH_SHORT).show();
            b.etMobile.setFocusableInTouchMode(true);
            b.etMobile.requestFocus();
            return false;
        }


        if (emailText.isEmpty()) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            b.etEmail.setFocusableInTouchMode(true);
            b.etEmail.requestFocus();
            return false;
        } else if (!Utils.myEmailValid(emailText)) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            b.etEmail.setFocusableInTouchMode(true);
            b.etEmail.requestFocus();
            return false;
        }

        if (addressText.isEmpty()) {
            b.etAddress.setError("enter address");
            return false;
        }
        if (cityText.isEmpty()) {
            b.etCity.setError("enter city");
            return false;
        }
        if (pinText.isEmpty()) {
            b.etPinCode.setError("enter pin code");
            return false;
        }*/

        return true;
    }


    private void ClickListener() {

        b.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        b.ivProfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();

            }
        });
    }


    void pickImage() {
        Log.e("sushil", "inside image pick");
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
       /* Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        someActivityResultLauncher.launch(chooserIntent);*/
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    private boolean checkForPermission(Context context) {

        if (

                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(context, "We need storage permission to execute the task", Toast.LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions((Activity) context, permissions, PERMISSION_CODE);
        return false;
    }

    public String multipartRequest(String urlTo, Map<String, Object> parmas, String filepath, String filefield, String fileMimeType) {
        Log.e("params", String.valueOf(parmas));
        //  Log.e("params1", filepath);
        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        InputStream inputStream = null;

        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";

        String result = "";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1024 * 1024;

        String[] q = filepath.split("/");
        int idx = q.length - 1;

        try {
            File file = new File(filepath);
            FileInputStream fileInputStream = new FileInputStream(file);
            Log.e("file", String.valueOf(file));
            java.net.URL url = new URL(urlTo);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestProperty("Authorization", auth);

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + filefield + "\"; filename=\"" + q[idx] + "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: " + fileMimeType + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);
            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                outputStream.flush();
            }

            outputStream.writeBytes(lineEnd);

            // Upload POST Data
            Iterator<String> keys = parmas.keySet().iterator();
            Log.e("hey-keys", String.valueOf(keys));
            while (keys.hasNext()) {
                String key = keys.next();
                String value = parmas.get(key).toString();
                Log.e("sushil", "response: " + key + " value");
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(value);
                outputStream.writeBytes(lineEnd);
            }

            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            Log.e("Connection response", String.valueOf(connection.getResponseCode()));
            Log.e("sushil", "response: " + connection.getResponseCode());
            if (connection.getResponseCode() == 201) {

                runOnUiThread(new Runnable() {
                    public void run() {

                        //  tv.setText("Upload Complete");
                        Toast.makeText(EditProfileActivity.this, "Profile update successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                });

            } else {
                Toast.makeText(EditProfileActivity.this, "Please Upload Image", Toast.LENGTH_SHORT).show();
            }
            inputStream = connection.getInputStream();

            result = this.convertStreamToString(inputStream);

            fileInputStream.close();
            inputStream.close();
            outputStream.flush();
            outputStream.close();

            return result;
        } catch (Exception e) {
//            logger.error(e);
//            throw new CustomException(e)
        }
        return "error";
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    public void PickiTonUriReturned() {

    }

    @Override
    public void PickiTonStartListener() {

    }

    @Override
    public void PickiTonProgressUpdate(int progress) {

    }

    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason) {
        Log.e("sushil", "path: " + path);
        profileImagePath = path;
        Glide.with(EditProfileActivity.this).load(new File(path)).into(b.ivProfile1);
    }

    @Override
    public void PickiTonMultipleCompleteListener(ArrayList<String> paths, boolean wasSuccessful, String Reason) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


}