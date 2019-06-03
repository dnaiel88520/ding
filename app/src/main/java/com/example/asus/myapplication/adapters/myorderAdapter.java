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

import com.example.asus.myapplication.model.order;
import com.example.asus.myapplication.R;
import com.example.asus.myapplication.myorderdetail;
import com.example.asus.myapplication.proupdata;

import java.util.List;

public class myorderAdapter extends RecyclerView.Adapter<myorderAdapter.myorderViewHolder> {

private Context mContext;
private List<order> mData;


    public myorderAdapter(Context mContext, List<order> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myorderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view =  inflater.inflate(R.layout.myorder_row_item,parent,false);
        myorderViewHolder myorderViewHolder = new myorderViewHolder(view);
        myorderViewHolder.view_orderdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,myorderdetail.class);



            }
        });


        return myorderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myorderViewHolder holder, int position) {
        holder.tv_sell.setText(mData.get(position).getSelname());
        holder.tv_buy.setText(mData.get(position).getBuyname());
        holder.tv_status.setText(mData.get(position).getStatus());
        holder.tv_time.setText(mData.get(position).getOrdertime());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myorderViewHolder extends RecyclerView.ViewHolder{
        TextView tv_sell;
        TextView tv_buy;
        TextView tv_status;
        TextView tv_time;
        LinearLayout view_orderdetail;










       public myorderViewHolder(View itemView) {
           super(itemView);

           view_orderdetail = itemView.findViewById(R.id.order);
           tv_sell = itemView.findViewById(R.id.sellname);
           tv_buy = itemView.findViewById(R.id.buyname);
           tv_status = itemView.findViewById(R.id.status);
           tv_time = itemView.findViewById(R.id.time);
       }
   }


}
