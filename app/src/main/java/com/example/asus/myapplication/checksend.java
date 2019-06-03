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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.adapters.discountAdpater;
import com.example.asus.myapplication.adapters.proshowAdapter;
import com.example.asus.myapplication.model.discount;
import com.example.asus.myapplication.model.drink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class checksend extends AppCompatActivity {
    private  final  String JSON_URL ="http://140.126.146.28/dindong/api/discount/findall";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<discount> discountList;
    private RecyclerView recyclerView;

     String name,status1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checksend);
        discountList = new ArrayList<>();
        recyclerView =findViewById(R.id.discount);
        name = "銘謝惠顧";
        status1="未使用";
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("優惠觀看管理");
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
                        discount discount = new discount();
                        if (!name.equals(jsonObject.getString("discount_context"))&&((status1.equals(jsonObject.getString("status"))))) {
                            discount.setContext(jsonObject.getString("discount_context"));
                            discount.setStatus(jsonObject.getString("status"));
                            discount.setTime(jsonObject.getString("dtime"));

                            discountList.add(discount);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecycleview(discountList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(checksend.this);
        requestQueue.add(request);

    }

    private void setuprecycleview(List<discount> discountList) {

        discountAdpater myadapter = new discountAdpater(this,discountList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }

}




























/*
        checksend = findViewById(R.id.checksend);
        new checksend.getData().execute();*/

/*
    class getData extends AsyncTask<String, String, String> {
        private String LOCALHOST = "140.126.146.28";
        HttpURLConnection urlConnection = null;


        protected String doInBackground(String... args) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL("http://" + LOCALHOST + "/dindong/api/discount/findall");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                urlConnection.setDoOutput(true);

                urlConnection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                char[] buffer = new char[1024];

                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line + "\n");
                }
                br.close();

                String jsonString = result.toString();

                System.out.println("JSON: " + jsonString);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return result.toString();
        }

        protected void onPostExecute(String result) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String a = jsonObject.getString("discount_context");
                    String b = jsonObject.getString("discount_buyname");
                    String c = jsonObject.getString("status");
                    checksend.append("優惠:"+a+"\n"+"擁有者:"+b+"\n"+"狀態:"+c+"\n");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/

