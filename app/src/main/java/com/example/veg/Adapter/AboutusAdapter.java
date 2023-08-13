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
import com.example.veg.models.AboutusModel;

import java.util.List;


public class AboutusAdapter extends RecyclerView.Adapter<AboutusAdapter.MyViewHolder> {
    public List<AboutusModel.AboutUs> psy;
    public Context context;

    public AboutusAdapter(List<AboutusModel.AboutUs> list, Context context) {
        this.psy = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutus_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvContent.setText(psy.get(position).content);
        holder.tvTitle.setText(psy.get(position).title);

        if (psy.get(position).image.isEmpty()) {
            holder.ivImage.setImageResource(R.drawable.bestsellerimage);
        } else {
            Glide.with(context).load(psy.get(position).image).into(holder.ivImage);
           // Picasso.get().load(psy.get(position).image).into(holder.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return this.psy.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView ivImage;
        TextView tvContent, tvTitle;

        MyViewHolder(View itemView) {
            super(itemView);


            ivImage = itemView.findViewById(R.id.ivImage);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvTitle = itemView.findViewById(R.id.tvTitle);


        }

    }
}
