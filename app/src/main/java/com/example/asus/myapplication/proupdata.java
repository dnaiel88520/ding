package com.example.asus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.activities.drinkActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class proupdata extends AppCompatActivity {
    EditText price, context;
    TextView name;
    Button send;
    ImageButton imgbtnback1;
    String seller;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proupdata);
        final RequestQueue queue = Volley.newRequestQueue(this);
        name = (TextView) findViewById(R.id.name);
        price = (EditText) findViewById(R.id.price);
        context = (EditText) findViewById(R.id.context);
        send = (Button) findViewById(R.id.send);
        imgbtnback1 = (ImageButton) findViewById(R.id.btnback11);
        Intent id = getIntent();
        seller = id.getStringExtra("drink_proname");
        name.setText(seller);


        imgbtnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                proupdata.this.finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String proname = name.getText().toString();
                final String proprice = price.getText().toString();
                final String procontext = context.getText().toString();
                String url = "http://140.126.146.28/dindong/api/produces/update";

                    final StringRequest mStringRequest = new StringRequest(Request.Method.PUT,
                            url, new Response.Listener<String>() {
                        @Override

                        public void onResponse(String response) {
                            Toast.makeText(getApplication(), "修改成功", Toast.LENGTH_SHORT).show();
                            proupdata.this.finish();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplication(), "母湯", Toast.LENGTH_SHORT).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("proname",seller);
                            params.put("proprice", proprice);
                            params.put("prodata", procontext);
                            return params;
                        }

                    };

                    queue.add(mStringRequest);
                }
        });
    }
}
