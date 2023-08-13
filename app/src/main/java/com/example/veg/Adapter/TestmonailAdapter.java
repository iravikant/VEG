package com.example.veg.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.R;
import com.example.veg.models.HomeModel;

import java.util.List;

public class TestmonailAdapter extends RecyclerView.Adapter<TestmonailAdapter.MyViewHolder> {
    public List<HomeModel.Testimonial> faqList;
    public Context context;

    public TestmonailAdapter(List<HomeModel.Testimonial> list, Context context) {
        this.faqList = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.testmonail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (faqList.get(position).image != null && !faqList.get(position).image.isEmpty()) {
            Glide.with(context).load(faqList.get(position).image).into(holder.ivImage);
        }
        holder.tvName.setText(faqList.get(position).name);
        holder.tvContent.setText(Html.fromHtml(faqList.get(position).content));

    }


    @Override
    public int getItemCount() {
        return this.faqList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName, tvContent;


        MyViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvContent = itemView.findViewById(R.id.tvContent);


        }

    }
}


