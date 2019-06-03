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

import  com.example.asus.myapplication.R;
import com.example.asus.myapplication.activities.MainActivity;
import com.example.asus.myapplication.model.order;
import com.example.asus.myapplication.orderrequest;
import com.example.asus.myapplication.produceshow;

import java.util.List;

public class sellorderAdapter extends  RecyclerView.Adapter<sellorderAdapter.orderViewHolder>{

     private Context mContext;
     private List<order> mData;

    public sellorderAdapter(Context mContext, List<order> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public orderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.sell_row_item,parent,false);
        final orderViewHolder orderViewHolder = new orderViewHolder(view);
        orderViewHolder.view_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(mContext, orderrequest.class);
                a.putExtra("buyname",mData.get(orderViewHolder.getAdapterPosition()).getBuyname());
                a.putExtra("sellname",mData.get(orderViewHolder.getAdapterPosition()).getSelname());
                a.putExtra("status",mData.get(orderViewHolder.getAdapterPosition()).getStatus());
                a.putExtra("time",mData.get(orderViewHolder.getAdapterPosition()).getOrdertime());
                a.putExtra("orderdetailid",mData.get(orderViewHolder.getAdapterPosition()).getOrderdetail());
                a.putExtra("buycontext",mData.get(orderViewHolder.getAdapterPosition()).getBuycontext());
                mContext.startActivity(a);

            }
        });



        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull orderViewHolder holder, int position) {
        holder.tv_orderdetail.setText(mData.get(position).getOrderdetail());
        holder.tv_buy.setText(mData.get(position).getBuyname());
        holder.tv_status.setText(mData.get(position).getStatus());
        holder.tv_sell.setText(mData.get(position).getSelname());
        holder.tv_time.setText(mData.get(position).getOrdertime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class orderViewHolder extends RecyclerView.ViewHolder{

        TextView tv_orderdetail;
        TextView tv_buy;
        TextView tv_status;
        TextView tv_sell;
TextView tv_time;
        LinearLayout view_order;

       public orderViewHolder(View itemView) {
           super(itemView);
tv_sell =itemView.findViewById(R.id.sellname);
           tv_orderdetail = itemView.findViewById(R.id.orderdetailid);
           tv_buy = itemView.findViewById(R.id.buyname);
           tv_status = itemView.findViewById(R.id.status);
       tv_time = itemView.findViewById(R.id.ordertime);
       view_order=itemView.findViewById(R.id.sell);
       }
   }
}

