package com.example.asus.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.adapters.orderdetailAdapter;
import com.example.asus.myapplication.adapters.sellorderAdapter;
import com.example.asus.myapplication.model.order;
import com.example.asus.myapplication.model.orderdetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class seller_order extends AppCompatActivity {

    private  final  String JSON_URL ="http://140.126.146.28/dindong/api/order/findall";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<order> orderList;
    private RecyclerView recyclerView;
    String status = "檢核中.....";
    String check;
TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order);
        recyclerView = findViewById(R.id.sellorder);
        orderList = new ArrayList<>();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("訂單接收");
        check = login.check;
        name = (TextView)findViewById(R.id.sellname);
        name.setText(check);
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
                        order order = new order();
                       if(check.equals(jsonObject.getString("sellname")) && (status.equals(jsonObject.getString("status")))) {
                           order.setSelname(jsonObject.getString("sellname"));
                           order.setOrderdetail(jsonObject.getString("orderdetailid"));
                            order.setStatus(jsonObject.getString("status"));
                            order.setBuyname(jsonObject.getString("buyname"));
                           order.setOrdertime(jsonObject.getString("ordertime"));
                            orderList.add(order);
                      }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecycleview(orderList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(seller_order.this);
        requestQueue.add(request);

    }

    private void setuprecycleview(List<order> orderList) {

        sellorderAdapter myadapter = new sellorderAdapter(this,orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}



