package com.example.asus.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    private Button button;
    TextView textView, usname;
    ImageButton imbtn_search;
    String seller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button nextPageBtn = (Button) findViewById(R.id.btn_set);
        Button nextPageBtn2 = (Button) findViewById(R.id.btn_teasoup);
        Button nextPageBtn3 = (Button) findViewById(R.id.btn_coco);
        Button nextPageBtn4 = (Button) findViewById(R.id.btn_dayung);
        Button nextPageBtn5 = (Button) findViewById(R.id.btn_game);
        Button nextPageBtn6 = (Button) findViewById(R.id.btn_myorder);
        Button nextPageBtn7 = (Button) findViewById(R.id.btn_curt);
        Button nextPageBtn8 = (Button) findViewById(R.id.btn_heart);
        Button nextPageBtn9 = (Button) findViewById(R.id.btn_alley);
        Button nextPageBtn10 = (Button) findViewById(R.id.btn_milkshop);
        Button nextPageBtn11 = (Button) findViewById(R.id.btn_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("大廳");
        ImageButton imbtn_search = (ImageButton) findViewById(R.id.imbtn_search);
        List<String> mUrlList = new ArrayList<>();

        final Spinner name = (Spinner) findViewById(R.id.seller_name);

        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,setting.class));
            }
        });

        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,teasoup.class));
            }
        });


        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,coco.class));

            }
        });

        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,dayung.class));

            }
        });
        nextPageBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,lotto.class));
            }
        });
        nextPageBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,myorder.class));
            }
        });
        nextPageBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,car.class));
            }
        });
        nextPageBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,heart.class));

            }
        });
        nextPageBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,alley.class));

            }
        });
        nextPageBtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,milkshop.class));

            }
        });
        nextPageBtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("buyname", seller);
                intent.putExtras(bundle);
                intent.setClass(home.this, order1.class);
                startActivity(intent);

            }
        });


        imbtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sell = name.getSelectedItem().toString();
                if (sell.equals("COCO都可")) {
                    startActivity(new Intent(home.this,coco.class));
                }
                if (sell.equals("清新福全")) {
                    startActivity(new Intent(home.this,heart.class));
                }
                if (sell.equals("茶湯會")) {
                    startActivity(new Intent(home.this,teasoup.class));
                }
                if (sell.equals("斜角巷")) {
                    startActivity(new Intent(home.this,alley.class));
                }
                if (sell.equals("迷克夏")) {
                    startActivity(new Intent(home.this,milkshop.class));
                }
                if (sell.equals("大苑子")) {
                    startActivity(new Intent(home.this,dayung.class));
                }
            }
        });

        }


    }




