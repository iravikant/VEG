package com.example.veg.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.veg.R;
import com.example.veg.models.SubcategoryModel;

import java.util.List;


public class SmallProductAdapter extends RecyclerView.Adapter<SmallProductAdapter.FAQViewHolder> {

        // private final String TAG =ShopAdapter.class.getSimpleName();
        private LayoutInflater inflater;
    private Context mContext;

    List<SubcategoryModel.sub_category> mList;
    public SmallProductAdapter(Context context, List<SubcategoryModel.sub_category> mList1) {

        mContext = context;
        mList = mList1;
    }

    public SmallProductAdapter(List<SubcategoryModel.sub_category> sub_category, Context context) {
        }

        @Override
        public FAQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            return new FAQViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item, parent, false), viewType);

        }

        @Override
        public void onBindViewHolder(FAQViewHolder holder, int position) {
            holder.setIsRecyclable(false);
            holder.update(position, holder);

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
            ImageView subcategoryimage;
            TextView textview1;


            FAQViewHolder(@NonNull View itemView, int viewType) {
                super(itemView);


                textview1 = itemView.findViewById(R.id.textview1);
                subcategoryimage = itemView.findViewById(R.id.subcategoryimage);


            }


            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("UseCompatLoadingForDrawables")
            public void update(final int position, FAQViewHolder holder)
            {
                textview1.setText(mList.get(position).name);

                Glide.with(mContext).load(mList.get(position).image).into(subcategoryimage);

            }


        }





}
