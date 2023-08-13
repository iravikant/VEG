package com.example.veg.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.veg.R;
import com.example.veg.models.BannerModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class BannerAdapter extends SliderViewAdapter<BannerAdapter.SliderAdapterVH> {
    public List<BannerModel.Banner> data;
    public Context context;

    public BannerAdapter(List<BannerModel.Banner> list, Context context) {
        this.data = list;
        this.context = context;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        if (data.get(position).image.isEmpty()) {
            viewHolder.iv_banner.setImageResource(R.drawable.banner);
        } else {
            Glide.with(context).load(data.get(position).image).into(viewHolder.iv_banner);
        }
    }
/*
    @Override
    public void onBindViewHolder(final adapters.BannerAdapter.MyViewHolder.MyViewHolder holder, final int position) {
        if (data.get(position).img_link.isEmpty()) {
            holder.iv_banner.setImageResource(R.drawable.consular1);
        } else {
            Picasso.get().load(data.get(position).img_link).into(holder.iv_banner);
        }*/

    @Override
    public int getCount() {
        return this.data.size();
    }

    class SliderAdapterVH extends ViewHolder {

        View itemView;
        ImageView iv_banner;

        SliderAdapterVH(View itemView) {
            super(itemView);


            iv_banner = itemView.findViewById(R.id.iv_banner);
            this.itemView = itemView;

        }

    }
}

