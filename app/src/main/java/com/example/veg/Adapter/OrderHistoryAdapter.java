package com.example.veg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.R;
import com.example.veg.models.OrderHistoryModel;
import com.google.gson.Gson;

import java.util.List;


public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {
    public List<OrderHistoryModel.OrderHistory> psy;
    public Context context;

    public OrderHistoryAdapter(List<OrderHistoryModel.OrderHistory> list, Context context) {
        this.psy = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvTitle.setText(psy.get(position).order_id);
        Glide.with(context).load(psy.get(position).orderlist.get(0).product.image).into(holder.ivImage);
        if (psy.get(position).status.equalsIgnoreCase("OrderPlaced")) {
            holder.tvStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.button));
            holder.tvStatus.setText(psy.get(position).status);
            holder.tvStatus.setTextColor(Color.parseColor("#096A00"));
        } else if (psy.get(position).status.equalsIgnoreCase("OrderPending")) {
            holder.tvStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.butbgred));
            holder.tvStatus.setText(psy.get(position).status);
            holder.tvStatus.setTextColor(Color.parseColor("#d6151c"));
        } else {
            holder.tvStatus.setText(psy.get(position).status);
        }

        holder.tvDate.setText(psy.get(position).created_at);
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,OrderDetailsActivity.class);
                intent.putExtra("model", new Gson().toJson(psy.get(position)));
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return this.psy.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView ivImage;
        TextView tvStatus, tvTitle, tvDate;

        MyViewHolder(View itemView) {
            super(itemView);


            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);


        }

    }
}
