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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.activities.MainActivity;
import com.example.asus.myapplication.activities.drinkActivity;
import com.example.asus.myapplication.adapters.RecycleViewAdapter;
import com.example.asus.myapplication.adapters.orderdetailAdapter;
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

public class prodetail extends AppCompatActivity {
    private  final  String JSON_URL ="http://140.126.146.28/dindong/api/orderdetail/findall";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<orderdetail> orderdetailList;
    private RecyclerView recyclerView;
    Button button,send;
  String orderdetailid,sell;
  String status="檢核中.....";
  EditText mcontext;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodetail);
        mcontext=(EditText)findViewById(R.id.buycontext);
        Intent id = getIntent();
        Bundle bundle = id.getExtras();
       sell = bundle.getString("orderdetailid");
        final RequestQueue queue = Volley.newRequestQueue(this);
        final Date dt =new Date();
        orderdetailList = new ArrayList<>();
        orderdetailid=produceshow.orderdetailid;
        recyclerView =findViewById(R.id.orderdetail);
        button =(Button)findViewById(R.id.button);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String dts=sdf.format(dt);
                final String  context = mcontext.getText().toString().trim();
                String url = "http://140.126.146.28/dindong/api/order/sell";

                final StringRequest mStringRequest = new StringRequest(Request.Method.PUT,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(), "送出成功!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(prodetail.this,home.class));
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
                        params.put("buycontext", context);

                        return params;
                    }

                };

                queue.add(mStringRequest);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prodetail.this.finish();

            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("訂單明細");
        jsonrequest();

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
                        if((jsonObject.getString("orderdetailid")).equals(orderdetailid)) {
                            orderdetail.setOrderdetailid(jsonObject.getString("orderdetailid"));
                            orderdetail.setProname(jsonObject.getString("proname"));
                            orderdetail.setQuantity(jsonObject.getString("quantity"));
                            orderdetail.setSugar(jsonObject.getString("sugar"));
                            orderdetail.setIce(jsonObject.getString("ice"));
                            orderdetail.setPrice(jsonObject.getString("price"));
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
        requestQueue = Volley.newRequestQueue(prodetail.this);
        requestQueue.add(request);

    }

    private void setuprecycleview(List<orderdetail> orderdetailList) {

        orderdetailAdapter myadapter = new orderdetailAdapter(this,orderdetailList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}



