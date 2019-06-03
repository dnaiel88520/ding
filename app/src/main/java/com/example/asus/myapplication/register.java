package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class register extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("註冊中心");
        Button nextPageBtn = (Button)findViewById(R.id.btn_user);
        Button nextPageBtn2 = (Button)findViewById(R.id.btn_sale);
        nextPageBtn.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {
                startActivity(new Intent(register.this,user_register.class));
            }
        });

        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,seller_register.class));
            }
        });

    }
}
