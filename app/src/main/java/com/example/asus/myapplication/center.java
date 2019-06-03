package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class center extends AppCompatActivity {
    private ImageButton imgbtnback;
    private Button button;

    TextView name;
   String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("會員管理");
        check=login.check;


        Button nextPageBtn = (Button)findViewById(R.id.btn_changepw);
        name = (TextView)findViewById(R.id.textView30);
        name.setText(check);

        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(center.this,changepw.class));
            }
        });


    }
}
