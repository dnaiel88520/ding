package com.example.asus.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.myapplication.chooseshow;
import com.example.asus.myapplication.model.drink;
import com.example.asus.myapplication.R;
import java.util.List;

public class produceAdapter extends RecyclerView.Adapter<produceAdapter.proViewHolder> {
    private Context mContext;
    private List<drink> mData;
    RequestOptions option;
    public produceAdapter(Context mContext, List<drink> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public proViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.coco_row_item,parent,false);
        final  proViewHolder viewHolder = new proViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,chooseshow.class);
                i.putExtra("sellname",mData.get(viewHolder.getAdapterPosition()).getSellname());
                i.putExtra("proname",mData.get(viewHolder.getAdapterPosition()).getProname());
                i.putExtra("proprice",mData.get(viewHolder.getAdapterPosition()).getProprice());
                i.putExtra("data",mData.get(viewHolder.getAdapterPosition()).getProdata());
                i.putExtra("img",mData.get(viewHolder.getAdapterPosition()).getImage_URL());

                mContext.startActivity(i);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull proViewHolder holder, int position) {
        holder.sell_name.setText(mData.get(position).getSellname());
        holder.pro_name.setText(mData.get(position).getProname());
        holder.pro_price.setText(mData.get(position).getProprice());
        holder.pro_context.setText(mData.get(position).getProdata());
        Glide.with(mContext).load(mData.get(position).getImage_URL()).apply(option).into(holder.pro_img);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class proViewHolder extends RecyclerView.ViewHolder{


        TextView pro_context;
        TextView pro_name;
        TextView sell_name;
        TextView pro_price;
        ImageView pro_img;
        LinearLayout view_container;



    public proViewHolder(View itemView) {
        super(itemView);
        view_container=itemView.findViewById(R.id.container);
        pro_name=itemView.findViewById(R.id.drink_name);
        sell_name=itemView.findViewById(R.id.sellername);
        pro_price=itemView.findViewById(R.id.price);
        pro_img =itemView.findViewById(R.id.thumbnail);
        pro_context=itemView.findViewById(R.id.context);

    }
}
}
