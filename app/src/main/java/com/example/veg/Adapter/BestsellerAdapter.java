package com.example.veg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.ProductDetailsActivity;
import com.example.veg.R;
import com.example.veg.SessionManager;
import com.example.veg.api.RetrofitClient;
import com.example.veg.models.AddToCartModel;
import com.example.veg.models.AddToWishlistModel;
import com.example.veg.models.HomeModel;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BestsellerAdapter extends RecyclerView.Adapter<BestsellerAdapter.MyViewHolder> {
    public List<HomeModel.ProductDetails> faqList;
    public Context context;

    public BestsellerAdapter(List<HomeModel.ProductDetails> list, Context context) {
        this.faqList = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_of_the_day_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (faqList.get(position).image != null && !faqList.get(position).image.isEmpty()) {
            Glide.with(context).load(faqList.get(position).image).into(holder.ivImage);
        }
        holder.tvName.setText(faqList.get(position).name);
        holder.tvPrice.setText(String.valueOf("Rs."+ faqList.get(position).selling_price +" /"+faqList.get(position).product_unit));
        holder.tvDiscount.setText(String.valueOf("Rs." + (faqList.get(position).mrp)));
        holder.llfv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("model", new Gson().toJson(faqList.get(position)));
                context.startActivity(intent);
            }
        });
        holder.ivAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SessionManager sessionManager = new SessionManager(context);
                LoginModel sessionModel = sessionManager.getLoginSession();
                ProfileModel profileModel = sessionManager.getUser();
                String user_id = String.valueOf(profileModel.user.id);
                Call<AddToCartModel> call = RetrofitClient.getInstance().getApi().addToCart("Bearer " + sessionModel.access_token, String.valueOf(user_id), String.valueOf(faqList.get(position).id),1);
                call.enqueue(new Callback<AddToCartModel>() {
                    @Override
                    public void onResponse(Call<AddToCartModel> call, Response<AddToCartModel> response) {
                        Log.e("SushilCart", new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddToCartModel> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }

        });
      /*  holder.ivAddToWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               holder.ivAddToWishList.setImageResource(R.drawable.like_red);

                SessionManager sessionManager = new SessionManager(context);
                LoginModel sessionModel = sessionManager.getLoginSession();
                ProfileModel profileModel = sessionManager.getUser();
                String user_id = String.valueOf(profileModel.user.id);
                Call<AddToWishlistModel> call = RetrofitClient.getInstance().getApi().addToWishlist("Bearer " + sessionModel.access_token, String.valueOf(user_id), String.valueOf(faqList.get(position).id),1);
                call.enqueue(new Callback<AddToWishlistModel>() {
                    @Override
                    public void onResponse(Call<AddToWishlistModel> call, Response<AddToWishlistModel> response) {
                        Log.e("SushilCart", new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Added to wishlist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddToWishlistModel> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }

        });*/
    }


    @Override
    public int getItemCount() {
        return this.faqList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage,ivAddToCart, ivAddToWishList;
        TextView tvName, tvPrice, tvDiscount;
        LinearLayout llfv;

        MyViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
            llfv = itemView.findViewById(R.id.llfv);
            ivAddToCart = itemView.findViewById(R.id.ivAddToCart);
            ivAddToWishList = itemView.findViewById(R.id.ivAddToWishList);


        }

    }


}