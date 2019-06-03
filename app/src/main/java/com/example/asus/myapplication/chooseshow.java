package com.example.asus.myapplication;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.Map;

public class chooseshow extends AppCompatActivity {
Button button,add;
String seller,sell;
String orderid,orderdetailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseshow);
        final Button btn = (Button) findViewById(R.id.buttonadd);
        final TextView textView = (TextView) findViewById(R.id.textView78);
        final TextView textView1 = (TextView)findViewById(R.id.textView83);
       final Button btn1=(Button)findViewById(R.id.buttonLess);
        button = (Button)findViewById(R.id.button32);
        add = (Button)findViewById(R.id.button17);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
       final String radiovalue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
       RadioGroup rg1 = (RadioGroup)findViewById(R.id.radioGroup2);
        final String radiovalue1 = ((RadioButton)findViewById(rg1.getCheckedRadioButtonId())).getText().toString();
        orderid = produceshow.orderid;
        orderdetailid=produceshow.orderdetailid;
        Intent id = getIntent();
        Bundle bundle = id.getExtras();
        seller = bundle.getString("buyname");
        sell = bundle.getString("sellname");
        final  int count =1;
        getSupportActionBar().hide();


       final String sellername = getIntent().getExtras().getString("sellname");
       final String drinkname = getIntent().getExtras().getString("proname");
       final String drinkprice = getIntent().getExtras().getString("proprice");
        String description = getIntent().getExtras().getString("data");
        String drinkimg = getIntent().getExtras().getString("img");



         textView1.setText(drinkprice);
        TextView se_name = findViewById(R.id.aa_sellname);
        TextView dr_name = findViewById(R.id.aa_drink_name);
        final TextView dr_price = findViewById(R.id.aa_price);
        TextView dr_data = findViewById(R.id.aa_description);
        ImageView dr_img = findViewById(R.id.aa_thumbnail);


        se_name.setText(sellername);
        dr_name.setText(drinkname);
        dr_price.setText(drinkprice);
        dr_data.setText(description);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                        textView1.setText(Integer.valueOf(textView1.getText().toString().trim())+Integer.valueOf(drinkprice.trim())+"");
                                       textView.setText(Integer.valueOf(textView.getText().toString()) + 1 + "");
                                       if (Integer.valueOf(textView.getText().toString()) > 0) {
                                           btn1.setEnabled(true);

                                       }
                                   }
                               });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(Integer.valueOf(textView1.getText().toString().trim())-Integer.valueOf(drinkprice.trim())+"");
                textView.setText(Integer.valueOf(textView.getText().toString()) - 1 + "");
                if (Integer.valueOf(textView.getText().toString()) == 0) {
                    btn1.setEnabled(false);
                }
                }

        }
        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(chooseshow.this, produceshow.class);
                Bundle bundle = new Bundle();
                bundle.putString("buyname", seller);
                bundle.putString("sellname",sell);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String price = String.valueOf(textView1.getText().toString().trim());
                final String quantity = String.valueOf(textView.getText().toString().trim());
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/orderdetail/create", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(), "新增成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(chooseshow.this, produceshow.class);
                        chooseshow.this.finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(chooseshow.this, "註冊失敗", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("orderid", orderid);
                        map.put("orderdetailid", orderdetailid);
                        map.put("proname", drinkname);
                        map.put("quantity", quantity);
                        map.put("ice", radiovalue1);
                        map.put("sugar", radiovalue);
                        map.put("sellname", sellername);
                        map.put("price", price);

                        return map;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(chooseshow.this);
                queue.add(stringRequest1);
            }
        });

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(drinkimg).apply(requestOptions).into(dr_img);

}
}








