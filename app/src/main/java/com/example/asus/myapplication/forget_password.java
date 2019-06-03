package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class forget_password extends AppCompatActivity {
    private EditText edt_id;
    private ImageButton imgbtnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        imgbtnback = (ImageButton) findViewById(R.id.back_login);
        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(forget_password.this, login.class);
                startActivity(intent);
            }
        });
    }
}
