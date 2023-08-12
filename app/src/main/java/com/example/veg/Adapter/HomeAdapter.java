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
import com.example.veg.HomeModel;
import com.example.veg.R;
import com.google.gson.Gson;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    public List<HomeModel.ProductDetails> faqList;
    public Context context;

    public HomeAdapter(List<HomeModel.ProductDetails> list, Context context) {
        this.faqList = list;
        this.context = context;
    }


    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new HomeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HomeAdapter.MyViewHolder holder, final int position) {
        if (faqList.get(position).image != null && !faqList.get(position).image.isEmpty()) {
            Glide.with(context).load(faqList.get(position).image).into(holder.ivImage);
        }
        holder.tvName.setText(faqList.get(position).name);
        holder.tvPrice.setText(String.valueOf("Rs."+ faqList.get(position).selling_price +" /"+faqList.get(position).product_unit));
        holder.tvDiscount.setText(String.valueOf("Rs."+(faqList.get(position).mrp)));
       /* holder.llfv.setOnClickListener(new View.OnClickListener() {
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
                SessionModel sessionModel = sessionManager.getLoginSession();
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
        holder.ivAddToWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ivAddToWishList.setImageResource(R.drawable.like_red);
                SessionManager sessionManager = new SessionManager(context);
                SessionModel sessionModel = sessionManager.getLoginSession();
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
        ImageView ivImage, ivAddToCart,ivAddToWishList;
        TextView tvName,tvPrice,tvDiscount;
        LinearLayout llfv;

        MyViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivPro);
            tvName = itemView.findViewById(R.id.tvProName);
            tvPrice = itemView.findViewById(R.id.tvMrp);
            tvDiscount = itemView.findViewById(R.id.tvDescription);



        }

    }


}
