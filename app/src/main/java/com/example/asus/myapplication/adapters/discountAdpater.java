package com.example.asus.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.myapplication.R;
import com.example.asus.myapplication.discount1;
import com.example.asus.myapplication.model.discount;
import com.example.asus.myapplication.proupdata;

import java.util.List;

public class discountAdpater extends RecyclerView.Adapter<discountAdpater.discountViewHolder>{

private Context mContext;
private List<discount>  mData;

    public discountAdpater(Context mContext, List<discount> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public discountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.pro_row_item,parent,false);
        final discountViewHolder discountViewHolder = new discountViewHolder(view);
        discountViewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,discount1.class);
                i.putExtra("discount_context",mData.get(discountViewHolder.getAdapterPosition()).getContext());
                i.putExtra("status",mData.get(discountViewHolder.getAdapterPosition()).getStatus());
                i.putExtra("dtime",mData.get(discountViewHolder.getAdapterPosition()).getTime());


                mContext.startActivity(i);
            }
        });

        return discountViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull discountViewHolder holder, int position) {
        holder.ds_context.setText(mData.get(position).getContext());
        holder.ds_status.setText(mData.get(position).getStatus());
        holder.ds_time.setText(mData.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class  discountViewHolder extends RecyclerView.ViewHolder{

        TextView ds_context;
        TextView  ds_status;
        TextView  ds_time;

        LinearLayout view_container;



        public discountViewHolder(View itemView) {
            super(itemView);
            ds_context = itemView.findViewById(R.id.context);
            ds_status = itemView.findViewById(R.id.status);
            ds_time = itemView.findViewById(R.id.dtime);
            view_container=itemView.findViewById(R.id.pro);
        }
    }

}
