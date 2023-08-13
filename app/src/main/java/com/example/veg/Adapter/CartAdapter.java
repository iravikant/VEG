package com.example.veg.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.CartQuantityListener;
import com.example.veg.R;
import com.example.veg.RemoveCartFragment;
import com.example.veg.RemoveCartListener;
import com.example.veg.models.CartListModel;

import java.util.List;

import ezy.ui.view.NumberStepper;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    public List<CartListModel.Cart> faqList;
    public Context context;
    RemoveCartListener removeCartListener;
    CartQuantityListener cartQuantityListener;

    public CartAdapter(List<CartListModel.Cart> list, Context context, RemoveCartListener removeCartListener, CartQuantityListener cartQuantityListener) {
        this.faqList = list;
        this.context = context;
        this.removeCartListener = removeCartListener;
        this.cartQuantityListener = cartQuantityListener;
    }


    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        return new CartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.MyViewHolder holder, final int position) {
        CartListModel.Cart model = faqList.get(position);
        if (model.product.image != null && !model.product.image.isEmpty()) {
            Glide.with(context).load(model.product.image).into(holder.ivimage);
        }
        holder.tvName.setText(model.product.name);
        holder.tvPrice.setText("Rs." + (model.product.selling_price) * model.quantity);
        holder.tvDiscount.setText("Rs." + (model.product.mrp) * model.quantity);

        holder.nsStepper.setOnValueChangedListener(null);
        holder.nsStepper.setValue(model.quantity);
        holder.nsStepper.setOnValueChangedListener(new NumberStepper.OnValueChangedListener() {
            @Override
            public void onValueChanged(NumberStepper view, int value) {
                Log.e("sushilvalue", String.valueOf(value));
                cartQuantityListener.onQuantityChanged(model.product_id, value);
            }

        });

        holder.ivDeleteCartRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RemoveCartFragment(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeCartListener.onRemove(model.product_id);

                    }
                }).show(((AppCompatActivity) context).getSupportFragmentManager(), "remove_cart_fragment");
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.faqList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivimage, ivDeleteCartRow;
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


        }

    }
}



