package com.example.veg.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veg.ProductDetailsActivity;
import com.example.veg.R;
import com.example.veg.models.ProductModel;

import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.FAQViewHolder> {

    // private final String TAG =ShopAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private Context mContext;


    List<ProductModel.product> mList;
    public ProductListAdapter(Context context, List<ProductModel.product> mList1) {

        mContext = context;
        mList = mList1;
    }

    @Override
    public FAQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new FAQViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fresh_vegetables, parent, false), viewType);

    }

    @Override
    public void onBindViewHolder(FAQViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.update(position, holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        else
            return mList.size();

    }

    public class FAQViewHolder extends RecyclerView.ViewHolder {


        LinearLayout map_open;
        ImageView vegitables;
        TextView name,waight,price,mrp;

        FAQViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            waight = itemView.findViewById(R.id.weight);
            price = itemView.findViewById(R.id.price);
            mrp = itemView.findViewById(R.id.mrp);
            vegitables = itemView.findViewById(R.id.ivImage);

        }


        @RequiresApi(api = Build.VERSION_CODES.M)
        @SuppressLint("UseCompatLoadingForDrawables")
        public void update(final int position, FAQViewHolder holder)
        {
            name.setText(mList.get(position).name);
            waight.setText(mList.get(position).weight);
            price.setText(mList.get(position).selling_price);
            mrp.setText(mList.get(position).mrp);
            Glide.with(mContext).load(mList.get(position).image).into(vegitables);

        }


    }


}
