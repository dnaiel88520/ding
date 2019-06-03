package com.example.asus.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminui extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminui);
        btn = (Button) findViewById(R.id.button12);
        Button nextPageBtn1 = (Button) findViewById(R.id.button10);
        Button nextPageBtn2 = (Button) findViewById(R.id.button11);
        Button nextPageBtn3 = (Button) findViewById(R.id.button13);
        Button nextPageBtn4 = (Button) findViewById(R.id.button14);

        btn.setOnClickListener(listener);


        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(adminui.this, account.class);
                startActivity(intent);
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(adminui.this, review.class);
                startActivity(intent);
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(adminui.this, login.class);
                startActivity(intent);
            }
        });
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(adminui.this, newaccount.class);
                startActivity(intent);
            }
        });


    }
    private Button.OnClickListener listener =new Button.OnClickListener(){
        public void onClick(View v){
            if(v.getId()==R.id.button12){
                new AlertDialog.Builder(adminui.this)
                        .setTitle("意見回饋")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("暫無訊息")
                        .setPositiveButton("掰掰", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }
        }
    };
    }

