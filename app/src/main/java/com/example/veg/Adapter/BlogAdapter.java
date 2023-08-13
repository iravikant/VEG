package com.example.veg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.R;
import com.example.veg.models.BlogModel;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;


public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder> {
    public List<BlogModel.Blog> faqList;
    public Context context;

    public BlogAdapter(List<BlogModel.Blog> list, Context context) {
        this.faqList = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.our_blog, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (faqList.get(position).image != null && !faqList.get(position).image.isEmpty()) {
            Glide.with(context).load(faqList.get(position).image).into(holder.imageview);
        }
        holder.txt_content.setText(faqList.get(position).title);
        holder.txt_description.setText(Html.fromHtml(faqList.get(position).content));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd MMM yyyy ");
        try {
            holder.txt_date.setText(sdf.format(new Date(Instant.parse(faqList.get(position).created_at).getEpochSecond()*1000)));
        }catch (Exception e){
            e.printStackTrace();
            holder.txt_date.setText(faqList.get(position).created_at);
        }
      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BlogActivity.class);
                intent.putExtra("modelblog",new Gson().toJson(faqList.get(position)));
                context.startActivity(intent);
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return this.faqList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview, ivAddToWishlist;
        TextView txt_content, txt_description, txt_date, weight;


        MyViewHolder(View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.imageview);
            txt_content = itemView.findViewById(R.id.txt_content);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_date = itemView.findViewById(R.id.txt_date);



        }

    }
}


