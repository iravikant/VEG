package com.example.veg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.Constants;
import com.example.veg.ProductListActivity;
import com.example.veg.R;
import com.example.veg.models.HomeModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    public List<HomeModel.CategoryOfTheProduct> faqList;
    public Context context;

    public CategoryAdapter(List<HomeModel.CategoryOfTheProduct> list, Context context) {
        this.faqList = list;
        this.context = context;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.MyViewHolder holder, final int position) {
        if (faqList.get(position).image != null && !faqList.get(position).image.isEmpty()) {
            Glide.with(context).load(faqList.get(position).image).into(holder.ivImage);
        }
        holder.name.setText(faqList.get(position).name);
        holder.idCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductListActivity.class);
                if (faqList.get(position).slug.equals("fruits")) {
                    intent.putExtra(Constants.TYPE, Constants.FRUITS);
                    context.startActivity(intent);
                } else if (faqList.get(position).slug.equals("vegetables")) {
                    intent.putExtra(Constants.TYPE, Constants.VEGETABLES);
                    context.startActivity(intent);
                } else if (faqList.get(position).slug.equals("meats")) {
                    intent.putExtra(Constants.TYPE, Constants.MEATS);
                    context.startActivity(intent);
                } else if (faqList.get(position).slug.equals("sprouts")) {
                    intent.putExtra(Constants.TYPE, Constants.SPOUTS);
                    context.startActivity(intent);
                } else if (faqList.get(position).slug.equals("dry-fruits")) {
                    Toast.makeText(context, "Data Not Found", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.faqList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView name, tvPrice, tvDiscount;
        MaterialCardView idCategory;

        MyViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            name = itemView.findViewById(R.id.name);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
            idCategory = itemView.findViewById(R.id.idCategory);


        }
    }
}