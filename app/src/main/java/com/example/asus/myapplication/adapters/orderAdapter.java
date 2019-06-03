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
import com.example.asus.myapplication.model.order;
import com.example.asus.myapplication.orderdetail;
import com.example.asus.myapplication.produceshow;


import java.util.List;

public class orderAdapter extends  RecyclerView.Adapter<orderAdapter.newViewHolder>{

    private Context mContext;
    private List<order> mData;

    public orderAdapter(Context mContext, List<order> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public newViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view =  inflater.inflate(R.layout.order_row_item,parent,false);
        final newViewHolder viewHolder = new newViewHolder(view);
        viewHolder.view_order.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent a = new Intent(mContext, produceshow.class);
                a.putExtra("buyname",mData.get(viewHolder.getAdapterPosition()).getBuyname());
                a.putExtra("sellname",mData.get(viewHolder.getAdapterPosition()).getSelname());
                a.putExtra("status",mData.get(viewHolder.getAdapterPosition()).getStatus());
                a.putExtra("time",mData.get(viewHolder.getAdapterPosition()).getOrdertime());
                a.putExtra("alreadyorder",mData.get(viewHolder.getAdapterPosition()).getAlreadyorder());
                a.putExtra("order_no",mData.get(viewHolder.getAdapterPosition()).getOrderid());
                a.putExtra("orderdetailid",mData.get(viewHolder.getAdapterPosition()).getOrderdetail());
                a.putExtra("ordertotal",mData.get(viewHolder.getAdapterPosition()).getOrdertotal());
                mContext.startActivity(a);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull newViewHolder holder, int position) {
        holder.tv_sell.setText(mData.get(position).getSelname());
        holder.tv_buy.setText(mData.get(position).getBuyname());
        holder.tv_status.setText(mData.get(position).getStatus());
        holder.tv_time.setText(mData.get(position).getOrdertime());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class newViewHolder extends RecyclerView.ViewHolder{

        TextView tv_sell;
        TextView tv_buy;
        TextView tv_status;
        TextView tv_time;
        LinearLayout view_order;




        public newViewHolder(View itemView) {
            super(itemView);
            view_order = itemView.findViewById(R.id.order);
            tv_sell = itemView.findViewById(R.id.sellname);
            tv_buy = itemView.findViewById(R.id.buyname);
            tv_status = itemView.findViewById(R.id.status);
            tv_time = itemView.findViewById(R.id.time);


        }
    }
}
