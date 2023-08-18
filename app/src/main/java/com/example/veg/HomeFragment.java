package com.example.veg;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.veg.Adapter.BannerAdapter;
import com.example.veg.Adapter.BestsellerAdapter;
import com.example.veg.Adapter.BlogAdapter;
import com.example.veg.Adapter.CategoryAdapter;
import com.example.veg.Adapter.FreshVegetableAdapter;
import com.example.veg.Adapter.FreshmeatAdapter;
import com.example.veg.Adapter.FruitlistAdapter;
import com.example.veg.Adapter.HomeAdapter;
import com.example.veg.Adapter.SpoutrsAdapter;
import com.example.veg.Adapter.TestmonailAdapter;
import com.example.veg.api.RetrofitClient;
import com.example.veg.databinding.ActivityHomeBinding;
import com.example.veg.databinding.FragmentHomeBinding;
import com.example.veg.models.BannerModel;
import com.example.veg.models.BlogModel;
import com.example.veg.models.CartListModel;
import com.example.veg.models.HomeModel;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
      /*  FragmentHomeBinding b;
    RecyclerView rvDeal, rvCategory, rvFresh, rvOurBlog;
    String url = "https://sabjeewala.seomantras.in/api/home";
    String accessToken;
    SessionManager sessionManager;
    LoginModel loginModel;
    TextView searchText;
    int user_id;
    ImageView ivCart;


        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";


        private String mParam1;
        private String mParam2;

        List<HomeModel.CategoryOfTheProduct> categoryOfTheProduct;

        public HomeFragment() {

        }

        public static HomeFragment newInstance(String param1, String param2) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            b = FragmentHomeBinding.inflate(inflater, container, false);
            View view = b.getRoot();
            sessionManager = new SessionManager(getContext());
            loginModel = sessionManager.getLoginSession();


            clicklistener();
            banner();
            getProfile();
            home();
            cout();
//            cart();


            return view;
        }

        private void cout() {
            new CountDownTimer(216010050, 1000) {

                public void onTick(long millisUntilFinished) {

                    // Used for formatting digit to be in 2 digits only

                    NumberFormat f = new DecimalFormat("00");

                    long hour = (millisUntilFinished / 3600000) % 24;

                    long min = (millisUntilFinished / 60000) % 60;

                    long sec = (millisUntilFinished / 1000) % 60;

                       b.viewDeal.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                    b.hh.setText(f.format(hour));
                    b.mm.setText(f.format(min));
                    b.ss.setText(f.format(sec));
                }
                // When the task is over it will print 00:00:00 there

                public void onFinish() {

                    *//* b. textView.setText("00:00:00");*//*
                    b.hh.setText("00");
                    b.mm.setText("00");
                    b.ss.setText("00");

                }

            }.start();
        }


       private void blogs() {


            Call<BlogModel> call = RetrofitClient.getInstance().getApi().blogs("Bearer " + sessionModel.access_token);
            call.enqueue(new Callback<BlogModel>() {
                @Override
                public void onResponse(Call<BlogModel> call, Response<BlogModel> response) {
                    Log.e("Blog response", new Gson().toJson(response.body()));
                    if (response.isSuccessful()) {
                        BlogAdapter blogAdapter = new BlogAdapter(response.body().blog, getContext());
                        b.rvOurBlog.setAdapter(blogAdapter);

                    }
                }

                @Override
                public void onFailure(Call<BlogModel> call, Throwable t) {
                    //Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }

        private void banner() {
            Call<BannerModel> call = RetrofitClient.getInstance().getApi().banner("Bearer " + loginModel.access_token);
            call.enqueue(new Callback<BannerModel>() {
                @Override
                public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                    Log.e("banner response", new Gson().toJson(response.body()));
                    if (response.isSuccessful()) {

                        BannerAdapter bannerAdapter = new BannerAdapter(response.body().banner, getContext());
                        b.imageSlider.setSliderAdapter(bannerAdapter);

                    }
                }

                @Override
                public void onFailure(Call<BannerModel> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);
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
//                        pincode = response.body().user.pincode;
//                        cart();
                        Glide.with(getContext()).load(response.body().user.img).into(b.ivImage);
                    }
                }

                @Override
                public void onFailure(Call<ProfileModel> call, Throwable t) {
                    // Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
                        if (response.body().code==400) {
                            b.ok.setVisibility(View.VISIBLE);
                            b.okkf.setVisibility(View.GONE);
                            Toast.makeText(getContext(), response.body().message, Toast.LENGTH_SHORT).show();

                        } else {
                            b.ok.setVisibility(View.GONE);
                            b.okkf.setVisibility(View.VISIBLE);
                            HomeAdapter homeAdapter = new HomeAdapter(response.body().deals, getContext());
                            b.rvDeal.setAdapter(homeAdapter);
                            CategoryAdapter categoryAdapter = new CategoryAdapter(response.body().category, getContext());
                            b.rvCategory.setAdapter(categoryAdapter);
                            FreshVegetableAdapter freshvegitablesAdapter = new FreshVegetableAdapter(response.body().vegetables, getContext());
                            LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            b.rvFresh.setLayoutManager(layoutManager1);
                            b.rvFresh.setAdapter(freshvegitablesAdapter);
                            FruitlistAdapter fruitAdapter = new FruitlistAdapter(response.body().fruits, getContext());
                            LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            b.rvPic.setLayoutManager(layoutManager2);
                            b.rvPic.setAdapter(fruitAdapter);
                            SpoutrsAdapter sproutAdapter = new SpoutrsAdapter(response.body().spouts, getContext());
                            LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            b.rvSprout.setLayoutManager(layoutManager3);
                            b.rvSprout.setAdapter(sproutAdapter);
                            FreshmeatAdapter freshmeatAdapter = new FreshmeatAdapter(response.body().meats, getContext());
                            LinearLayoutManager layoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            b.freshmeatRecycleview.setLayoutManager(layoutManager4);
                            b.freshmeatRecycleview.setAdapter(freshmeatAdapter);
                            BestsellerAdapter bestsellerAdapter = new BestsellerAdapter(response.body().sellers, getContext());
                            LinearLayoutManager layoutManager5 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            b.bestselerRecycleview.setLayoutManager(layoutManager5);
                            b.bestselerRecycleview.setAdapter(bestsellerAdapter);
                            TestmonailAdapter adapter = new TestmonailAdapter(response.body().testimonial, getContext());
                            LinearLayoutManager layoutManager6 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            b.testmonialRecycleview.setLayoutManager(layoutManager6);
                            b.testmonialRecycleview.setAdapter(adapter);
                        }


                    }
                }

                @Override
                public void onFailure(Call<HomeModel> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }

        private void clicklistener() {
            b.navigation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), NavigationActivity.class);
                    getActivity().startActivity(intent);
                }
            });

            b.viewCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProductListActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.CATEGORY);
                    getActivity().startActivity(intent);
                }
            });
            b.llDeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProductListActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.DEAL);
                    getActivity().startActivity(intent);
                }
            });
            b.cartHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), CartActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.DEAL);
                    getActivity().startActivity(intent);
                }
            });
            b.tvFreshVegetable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProductListActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.VEGETABLES);
                    getActivity().startActivity(intent);
                }
            });

            b.tvFreshFruit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProductListActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.FRUITS);
                    getActivity().startActivity(intent);
                }
            });
            b.tvSpouts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProductListActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.SPOUTS);
                    getActivity().startActivity(intent);
                }
            });
            b.tvMeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProductListActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.MEATS);
                    getActivity().startActivity(intent);
                }
            });
            b.tvSeller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProductListActivity.class);
                    intent.putExtra(Constants.TYPE, Constants.SELLERS);
                    getActivity().startActivity(intent);
                }
              });
        }*/
    }