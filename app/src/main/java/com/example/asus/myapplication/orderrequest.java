package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.adapters.orderdetailAdapter;
import com.example.asus.myapplication.adapters.sellorderAdapter;
import com.example.asus.myapplication.model.order;
import com.example.asus.myapplication.model.orderdetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class orderrequest extends AppCompatActivity {
    private  final  String JSON_URL ="http://140.126.146.28/dindong/api/orderdetail/findall";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<orderdetail> orderdetailList;
    private RecyclerView recyclerView;
    String status = "已完成";
    String check;
    TextView buytext;
    EditText sellcontext;
    Button send;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderrequest);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final Date dt =new Date();
        recyclerView = findViewById(R.id.catchorder);
        orderdetailList = new ArrayList<>();
        String context = getIntent().getExtras().getString("buycontext");
        buytext=(TextView)findViewById(R.id.buytext);
        buytext.setText(context);
        sellcontext=(EditText)findViewById(R.id.sellcontext);
        send = (Button)findViewById(R.id.button3);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("訂單處理");
        check = login.check;
        jsonrequest();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String dts=sdf.format(dt);
                final String  context = sellcontext.getText().toString().trim();
                final String orderdetailid = getIntent().getExtras().getString("orderdetailid");
                String url = "http://140.126.146.28/dindong/api/order/send";

                final StringRequest mStringRequest = new StringRequest(Request.Method.PUT,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(), "送出成功!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(orderrequest.this, seller_home.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication(), "送出失敗，請查看!!", Toast.LENGTH_SHORT).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("ordertime", dts);
                        params.put("status", status);
                        params.put("orderdetailid", orderdetailid);
                        params.put("ordercontext", context);

                        return params;
                    }

                };

                queue.add(mStringRequest);
            }
        });
            }

        private void jsonrequest() {

            request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    JSONObject jsonObject = null;

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            orderdetail orderdetail = new orderdetail();
                            if(check.equals(jsonObject.getString("sellname"))) {
                                orderdetail.setSellname(jsonObject.getString("sellname"));
                                orderdetail.setOrderdetailid(jsonObject.getString("orderdetailid"));
                                orderdetail.setProname(jsonObject.getString("proname"));
                                orderdetail.setIce(jsonObject.getString("ice"));
                                orderdetail.setSugar(jsonObject.getString("sugar"));
                                orderdetail.setPrice(jsonObject.getString("price"));
                                orderdetail.setQuantity(jsonObject.getString("quantity"));
                                orderdetailList.add(orderdetail);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    setuprecycleview(orderdetailList);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue = Volley.newRequestQueue(orderrequest.this);
            requestQueue.add(request);

        }

        private void setuprecycleview(List<orderdetail> orderdetailList) {

            orderdetailAdapter myadapter = new orderdetailAdapter(this,orderdetailList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            recyclerView.setAdapter(myadapter);
        }
    }




