package com.example.asus.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.KeyEvent;

public class setting extends AppCompatActivity {
    private ImageButton imgbtnback;
    private Button btn;
String seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btn = (Button) findViewById(R.id.btn_conn);
        Button nextPageBtn = (Button) findViewById(R.id.btn_comm);
        Button nextPageBtn1 = (Button) findViewById(R.id.btn_loginout);
        Button nextPageBtn2 = (Button) findViewById(R.id.btn_center);
        Button nextPageBtn3 = (Button) findViewById(R.id.btn_checksend);
        Button nextPageBtn4 = (Button) findViewById(R.id.btn_history);
        btn.setOnClickListener(listener);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("設定");


        nextPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setting.this,comment.class));
            }
        });
        nextPageBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(setting.this , login.class);
                startActivity(intent);
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             startActivity(new Intent(setting.this,center.class));
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setting.this,checksend.class));
            }
        });
        nextPageBtn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setting.this,history.class));
            }
        });

    }

    private Button.OnClickListener listener =new Button.OnClickListener(){
        public void onClick(View v){
            if(v.getId()==R.id.btn_conn){
                new AlertDialog.Builder(setting.this)
                        .setTitle("聯絡我們")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("請撥打0975032540，或寄信給dindong@gmail.com")
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // TODO Auto-generated method stub

                                    }
                                }).show();

            }
        }
    };
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(setting.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束應用程式嗎?可是不能走這裡!!")
                    .setIcon(R.drawable.ic_launcher)

                    .setNegativeButton("OK",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
        }
        return true;
    }

}
