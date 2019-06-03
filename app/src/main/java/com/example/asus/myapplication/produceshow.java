package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.adapters.produceAdapter;
import com.example.asus.myapplication.model.drink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class produceshow extends AppCompatActivity {
    private  final  String JSON_URL ="http://140.126.146.28/dindong/api/produces/findall";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<drink> drinkList;
    private RecyclerView recyclerView;
    private TextView name;
    String seller ,sellname,sell ;
    public static String orderdetailid;
    public static String orderid;
    Button detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produceshow);
        drinkList = new ArrayList<>();
        recyclerView =findViewById(R.id.show);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("飲料選擇");
       // Intent id = getIntent();
        //Bundle bundle = id.getExtras();
        //sell = bundle.getString("sellname");
        detail=(Button)findViewById(R.id.detail);
        sellname = getIntent().getExtras().getString("sellname");
         String orderstatus = getIntent().getExtras().getString("status");
         String alreadyorder = getIntent().getExtras().getString("alreadyorder");
        orderid = getIntent().getExtras().getString("order_no");
         orderdetailid =  getIntent().getExtras().getString("orderdetailid");
        name = (TextView)findViewById(R.id.sellname);
        name.setText(sellname);
        jsonrequest();
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(produceshow.this, prodetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("orderdetailid", seller);
                intent.putExtras(bundle);
                startActivity(intent);

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

                        drink drink = new drink();
                        if(sellname.equals(jsonObject.getString("sellname"))) {
                            drink.setSellname(jsonObject.getString("sellname"));
                            drink.setProname(jsonObject.getString("proname"));
                            drink.setProprice(jsonObject.getString("proprice"));
                            drink.setProdata(jsonObject.getString("prodata"));
                            drink.setImage_URL(jsonObject.getString("image"));
                            drinkList.add(drink);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecycleview(drinkList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(produceshow.this);
        requestQueue.add(request);

    }

    private void setuprecycleview(List<drink> drinkList) {

        produceAdapter myadapter = new produceAdapter(this,drinkList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}


