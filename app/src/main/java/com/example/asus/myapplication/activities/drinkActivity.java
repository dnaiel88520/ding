package com.example.asus.myapplication.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.myapplication.R;
import com.example.asus.myapplication.home;
import com.example.asus.myapplication.login;
import com.example.asus.myapplication.order1;
import com.example.asus.myapplication.proupdata;
import com.example.asus.myapplication.register;
import com.example.asus.myapplication.seller_manage;

public class drinkActivity extends AppCompatActivity {


    String seller;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

         button =(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drinkActivity.this.finish();

            }
        });

       getSupportActionBar().hide();

       String sellername = getIntent().getExtras().getString("sellname");
        String drinkname = getIntent().getExtras().getString("proname");
        String drinkprice = getIntent().getExtras().getString("proprice");
       String description = getIntent().getExtras().getString("data");
       String drinkimg = getIntent().getExtras().getString("img");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);


        TextView se_name = findViewById(R.id.aa_sellname);
        TextView dr_name = findViewById(R.id.aa_drink_name);
        TextView dr_price = findViewById(R.id.aa_price);
        TextView dr_data = findViewById(R.id.aa_description);
        ImageView dr_img = findViewById(R.id.aa_thumbnail);


        se_name.setText(sellername);
        dr_name.setText(drinkname);
        dr_price.setText(drinkprice);
        dr_data.setText(description);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(drinkimg).apply(requestOptions).into(dr_img);

        collapsingToolbarLayout.setTitle(drinkname);

    }
}
