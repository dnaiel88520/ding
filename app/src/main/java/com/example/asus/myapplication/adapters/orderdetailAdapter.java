package com.example.asus.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.myapplication.R;
import com.example.asus.myapplication.model.orderdetail;

import java.util.List;

public class orderdetailAdapter extends RecyclerView.Adapter<orderdetailAdapter.orderViewHolder>{

private Context mContext;
private List<orderdetail> mData;


    public orderdetailAdapter(Context mContext, List<orderdetail> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public orderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.detail_row_item,parent,false);

        return new orderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getProname());
        holder.orderdetailid.setText(mData.get(position).getOrderdetailid());
        holder.quantity.setText(mData.get(position).getQuantity());
        holder.ice.setText(mData.get(position).getIce());
        holder.sugar.setText(mData.get(position).getSugar());
        holder.price.setText(mData.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class orderViewHolder extends RecyclerView.ViewHolder {

         TextView name;
         TextView orderdetailid;
         TextView quantity;
         TextView ice;
         TextView sugar;
         TextView price;









        public orderViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.drink_name);
            orderdetailid = itemView.findViewById(R.id.orderdetail);
            quantity = itemView.findViewById(R.id.quantity);
            ice = itemView.findViewById(R.id.ice);
            sugar = itemView.findViewById(R.id.sugar);
            price = itemView.findViewById(R.id.price);
        }
    }
}