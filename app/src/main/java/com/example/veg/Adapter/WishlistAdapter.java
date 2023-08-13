package com.example.veg.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.CartQuantityListener;
import com.example.veg.R;
import com.example.veg.RemoveCartListener;
import com.example.veg.SessionManager;
import com.example.veg.api.RetrofitClient;
import com.example.veg.models.AddToCartModel;
import com.example.veg.models.LoginModel;
import com.example.veg.models.ProfileModel;
import com.example.veg.models.WishlistModel;
import com.google.gson.Gson;

import java.util.List;

import ezy.ui.view.NumberStepper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder> {
    public List<WishlistModel.Wishlist> faqList;
    public Context context;
    RemoveCartListener removeCartListener;
    CartQuantityListener cartQuantityListener;

    public WishlistAdapter(List<WishlistModel.Wishlist> list, Context context,RemoveCartListener removeCartListener, CartQuantityListener cartQuantityListener) {
        this.faqList = list;
        this.context = context;
        this.removeCartListener = removeCartListener;
        this.cartQuantityListener = cartQuantityListener;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        WishlistModel.Wishlist model = faqList.get(position);
        if (model.product.image != null && !model.product.image.isEmpty()) {
            Glide.with(context).load(model.product.image).into(holder.ivimage);
        }
        holder.tvName.setText(model.product.name);
        holder.tvPrice.setText("Rs." + (model.product.selling_price * model.quantity));
        holder.tvDiscount.setText("Rs." + (model.product.mrp) * model.quantity);
  /*      holder.nsStepper.setOnValueChangedListener(null);
        holder.nsStepper.setValue(model.quantity);
        holder.nsStepper.setOnValueChangedListener(new com.webdigitalmantra.sabjeewala.NumberStepper.OnValueChangedListener() {
            @Override
            public void onValueChanged(com.webdigitalmantra.sabjeewala.NumberStepper view, int value) {
                Log.e("sushilvalue", String.valueOf(value));
                cartQuantityListener.onQuantityChanged(String.valueOf(model.product_id), value);
            }
        });
        holder.ivDeleteCartRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RemoveCartFragment(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeCartListener.onRemove(String.valueOf(model.product_id));

                    }
                }).show(((AppCompatActivity) context).getSupportFragmentManager(), "remove_cart_fragment");
            }
        });*/
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(context);
                LoginModel sessionModel = sessionManager.getLoginSession();
                ProfileModel profileModel = sessionManager.getUser();
                String user_id = String.valueOf(profileModel.user.id);
                Log.e("SushilCart", ""+String.valueOf(profileModel.user.id));
                Call<AddToCartModel> call = RetrofitClient.getInstance().getApi().addToCart("Bearer " + sessionModel.access_token, String.valueOf(user_id), String.valueOf(faqList.get(position).product_id),1);
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


    }


    @Override
    public int getItemCount() {
        return this.faqList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivimage, ivDeleteCartRow, ivAdd;
        TextView tvName, tvPrice, tvDiscount, weight;
      NumberStepper nsStepper;


        MyViewHolder(View itemView) {
            super(itemView);

            ivimage = itemView.findViewById(R.id.ivimage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
            nsStepper = itemView.findViewById(R.id.nsStepper);
            ivDeleteCartRow = itemView.findViewById(R.id.ivDeleteCartRow);
            ivAdd = itemView.findViewById(R.id.ivAdd);


        }

    }
}


