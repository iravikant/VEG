package com.example.veg.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.R;
import com.example.veg.WishListActivity;
import com.example.veg.models.WishlistModel;

import java.util.List;

import ezy.ui.view.NumberStepper;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder> {
    public List<WishlistModel.Wishlist> faqList;
    public Context context;

    public WishlistAdapter(List<WishlistModel.Wishlist> list, Context context) {
        this.faqList = list;
        this.context = context;
    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {WishlistModel.Wishlist model = faqList.get(position);
        if (model.product.image != null && !model.product.image.isEmpty()) {
            Glide.with(context).load(model.product.image).into(holder.ivimage);
        }
        holder.tvName.setText(faqList.get(position).product.name);
        holder.tvPrice.setText("Rs." + (model.product.selling_price * model.quantity));
        holder.tvDiscount.setText("Rs." + (model.product.mrp) * model.quantity);


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


