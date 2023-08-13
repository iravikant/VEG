package com.example.veg.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.R;
import com.example.veg.databinding.OrdedetailsHistoryItemBinding;
import com.example.veg.models.OrderHistoryModel;

import java.util.List;

public class OrderHistoryDetailsAdapter extends RecyclerView.Adapter<OrderHistoryDetailsAdapter.MyViewHolder> {
    public List<OrderHistoryModel.Orderlist> psy;
    public Context context;

    public OrderHistoryDetailsAdapter(List<OrderHistoryModel.Orderlist> list, Context context) {
        this.psy = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordedetails_history_item, parent, false);
        return new MyViewHolder(OrdedetailsHistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.b.tvTitle.setText(psy.get(position).product.name);
        holder.b.tvDate.setText(psy.get(position).created_at);
        holder.b.tvStatus.setText(psy.get(position).product.status);
        Glide.with(context).load(psy.get(position).product.image).into(holder.b.ivImage);


        if (psy.get(position).product.status.equalsIgnoreCase("approved")) {
            holder.b.tvStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.button));
            holder.b.tvStatus.setText(psy.get(position).product.status);
            holder.b.tvStatus.setTextColor(Color.parseColor("#096A00"));
        } else if (psy.get(position).product.status.equalsIgnoreCase("pending")) {
            holder.b.tvStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.butbgred));
            holder.b.tvStatus.setText(psy.get(position).product.status);
            holder.b.tvStatus.setTextColor(Color.parseColor("#d6151c"));
        } else {
            holder.b.tvStatus.setText(psy.get(position).product.status);
        }
        holder.b.add.setText(psy.get(position).product.additional_info);
    }

    @Override
    public int getItemCount() {
        return this.psy.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private OrdedetailsHistoryItemBinding b;

        public MyViewHolder(OrdedetailsHistoryItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }
    }
}





