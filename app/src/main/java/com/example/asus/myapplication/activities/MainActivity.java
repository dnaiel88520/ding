package com.example.asus.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.R;
import com.example.asus.myapplication.adapters.RecycleViewAdapter;
import com.example.asus.myapplication.model.drink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  final  String JSON_URL ="http://192.168.0.103/dindong/api/produces/findall";
   private JsonArrayRequest request;
   private RequestQueue requestQueue;
   private List<drink> drinkList;
   private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    drinkList = new ArrayList<>();
    recyclerView =findViewById(R.id.recyclerviewid);
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
                        drink.setSellname(jsonObject.getString("sellname"));
                        drink.setProname(jsonObject.getString("proname"));
                        drink.setProprice(jsonObject.getString("proprice"));
                        drink.setProdata(jsonObject.getString("prodata"));
                        drink.setImage_URL(jsonObject.getString("image"));
                        drinkList.add(drink);
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
requestQueue = Volley.newRequestQueue(MainActivity.this);
requestQueue.add(request);

    }

    private void setuprecycleview(List<drink> drinkList) {

        RecycleViewAdapter myadapter = new RecycleViewAdapter(this,drinkList);
recyclerView.setLayoutManager(new LinearLayoutManager(this));

recyclerView.setAdapter(myadapter);
    }
}
