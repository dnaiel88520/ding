package com.example.asus.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class discount1 extends AppCompatActivity {
TextView name1,context1;
    String check,status;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount1);
        final RequestQueue queue = Volley.newRequestQueue(this);
        name1=(TextView)findViewById(R.id.name);
        context1=(TextView)findViewById(R.id.context);
        send=(Button)findViewById(R.id.button8);
        check=login.check;
        final String context = getIntent().getExtras().getString("discount_context");
        status="已使用";
        String time = getIntent().getExtras().getString("dtime");
        name1.setText(check);
        context1.setText(context);
    send.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "http://140.126.146.28/dindong/api/discount/update";

            final StringRequest mStringRequest = new StringRequest(Request.Method.PUT,
                    url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplication(), "兌換成功!", Toast.LENGTH_SHORT).show();

                    discount1.this.finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplication(), "兌換失敗，請查看!!", Toast.LENGTH_SHORT).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("discount_context", context);
                    params.put("discount_buyname", check);
                    params.put("status", status);
                    return params;
                }

            };

            queue.add(mStringRequest);
        }
    });

    }
    }



