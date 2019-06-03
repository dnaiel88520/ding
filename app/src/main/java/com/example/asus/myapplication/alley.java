package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.adapters.produceAdapter;
import com.example.asus.myapplication.adapters.proshowAdapter;
import com.example.asus.myapplication.model.drink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class alley extends AppCompatActivity {
    private  final  String JSON_URL ="http://192.168.0.103/dindong/api/produces/findall";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<drink> drinkList;
    private RecyclerView recyclerView;
    String name = "斜角巷";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alley);
        final RequestQueue queue = Volley.newRequestQueue(this);
        drinkList = new ArrayList<>();
        recyclerView =findViewById(R.id.alley);
        String name ="斜角巷";
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("斜角巷");
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
                        drink drink = new drink();
                        if (name.equals(jsonObject.getString("sellname"))) {
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
        requestQueue = Volley.newRequestQueue(alley.this);
        requestQueue.add(request);

    }

    private void setuprecycleview(List<drink> drinkList) {

        proshowAdapter myadapter = new proshowAdapter(this,drinkList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }

}