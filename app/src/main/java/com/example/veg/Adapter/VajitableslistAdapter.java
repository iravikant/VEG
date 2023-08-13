package com.example.veg.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veg.R;


public class VajitableslistAdapter extends RecyclerView.Adapter<VajitableslistAdapter.FAQViewHolder> {

    private final String TAG = VajitableslistAdapter.class.getSimpleName();


    //ArrayList<TimeLineModel> mPackageList;
    private LayoutInflater inflater;
    private Context mContext;

    //, ArrayList<TimeLineModel> mDataList

    public VajitableslistAdapter(Context context/*, HomeFragment homeFragment*/) {
        mContext = context;
        //  mPackageList = mDataList;
    }

    @Override
    public FAQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new FAQViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vajitableslist, parent, false), viewType);

    }

    @Override
    public void onBindViewHolder(FAQViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.update(position, holder);

    }

    @Override
    public int getItemCount() {
      /*  if (mPackageList == null)
            return 0;
        else
            return mPackageList.size();*/
        return 20;
    }

    public class FAQViewHolder extends RecyclerView.ViewHolder {

        TextView txt_seminar_module_name;

        CardView cardviewrent;
        ImageView img_product;


        FAQViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

/* /  cardviewrent =itemView.findViewById(R.id.cardviewrent);
img_product =itemView.findViewById(R.id.img_product);


/*

            tv_Title = itemView.findViewById(R.id.tv_Title);
            //iv_logo = itemView.findViewById(R.id.iv_logo);
            timeline = itemView.findViewById(R.id.timeline);
*/
            img_product = itemView.findViewById(R.id.img_product);


        }


        @RequiresApi(api = Build.VERSION_CODES.M)
        @SuppressLint("UseCompatLoadingForDrawables")
        public void update(final int position, FAQViewHolder holder) {
            //  tv_Title.setText(mPackageList.get(position).getMessage());

          /*  img_product.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {*/
/*                    Intent intent=new Intent(mContext, ProductdescriptionActivity.class);
                    mContext.startActivity(intent);*/


                                             /*      ProductActivity activity = (ProductActivity) view.getContext();
                                                   Fragment myFragment = new ProductdetailFragment();
                                                   activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

                                               }
                                           }


            );*/


        }
    }
}




