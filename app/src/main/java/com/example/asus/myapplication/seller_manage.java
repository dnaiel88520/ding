package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.adapters.RecycleViewAdapter;
import com.example.asus.myapplication.model.drink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class seller_manage extends AppCompatActivity  {
    private  final  String JSON_URL ="http://140.126.146.28/dindong/api/produces/findall";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<drink> drinkList;
    private RecyclerView recyclerView;
    ImageButton imgbtnback;
    String check;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_manage);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("賣方管理");
        btnAdd=(Button)findViewById(R.id.button6);
        drinkList = new ArrayList<>();
        recyclerView =findViewById(R.id.recycleview1);
         check = login.check;
         jsonrequest();



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seller_manage.this,seller_shelves.class));

            }
        });

}

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = new JSONObject();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        drink drink = new drink();
                        if(check.equals(jsonObject.getString("sellname"))) {
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
                return;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(seller_manage.this);
        requestQueue.add(request);

    }

    private void setuprecycleview(List<drink> drinkList) {

        RecycleViewAdapter myadapter = new RecycleViewAdapter(this,drinkList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }



}


