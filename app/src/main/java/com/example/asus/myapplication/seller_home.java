package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class seller_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("賣家管理");
        Button nextPageBtn = (Button)findViewById(R.id.btn_drink_management2);
        Button nextPageBtn1 = (Button)findViewById(R.id.btn_myorder);
        Button nextPageBtn2 = (Button)findViewById(R.id.btn_comment);
        Button nextPageBtn3 = (Button)findViewById(R.id.button5);

        nextPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seller_home.this,seller_manage.class));
            }
        });
        nextPageBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seller_home.this,seller_order.class));
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seller_home.this,opinion.class));
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(seller_home.this , login.class);
                startActivity(intent);
            }
        });


    }
}
