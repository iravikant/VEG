package com.example.veg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.ProductDetailsActivity;
import com.example.veg.R;
import com.example.veg.SessionManager;
import com.example.veg.api.RetrofitClient;
import com.example.veg.models.AddToCartModel;
import com.example.veg.models.HomeModel;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FruitlistAdapter extends RecyclerView.Adapter<FruitlistAdapter.MyViewHolder> {
    public List<HomeModel.ProductDetails> faqList;
    public Context context;

    public FruitlistAdapter(List<HomeModel.ProductDetails> list, Context context) {
        this.faqList = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.freshfruit, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (faqList.get(position).image != null && !faqList.get(position).image.isEmpty())  {

                Glide.with(context).load(faqList.get(position).image).into(holder.img_product);

        }
        holder.name.setText(faqList.get(position).name);
       // holder.txt_price1.setText(String.valueOf("Rs." + (faqList.get(position).selling_price)));
        holder.txt_price1.setText(String.valueOf("Rs."+ faqList.get(position).selling_price +" /"+faqList.get(position).product_unit));
         holder.tvDiscount.setText(String.valueOf("Rs."+(faqList.get(position).mrp)));
        holder.txt_price.setText(String.valueOf((faqList.get(position).weight)));
        holder.rlFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("model", new Gson().toJson(faqList.get(position)));
                context.startActivity(intent);
            }
        });


        holder.tvAddToCart.setOnClickListener(new View.OnClickListener() {
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
     /*   holder.image_hert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.image_hert.setImageResource(R.drawable.like_red);
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
        ImageView img_product,image_hert;
        TextView name, txt_price1, tvDiscount,txt_price, tvAddToCart;
        RelativeLayout rlFruit;


        MyViewHolder(View itemView) {
            super(itemView);

            img_product = itemView.findViewById(R.id.img_product);
            name = itemView.findViewById(R.id.name);
            txt_price1 = itemView.findViewById(R.id.txt_price1);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
            txt_price = itemView.findViewById(R.id.txt_price);
            rlFruit = itemView.findViewById(R.id.rlFruit);
            tvAddToCart = itemView.findViewById(R.id.tvAddToCart);
            image_hert = itemView.findViewById(R.id.image_hert);



        }

    }


}

