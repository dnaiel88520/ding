package com.example.asus.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.adapters.orderAdapter;
import com.example.asus.myapplication.model.order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class order1 extends AppCompatActivity {

    private  final  String JSON_URL ="http://140.126.146.28/dindong/api/order/findall";
    private JsonArrayRequest request;
    private  RequestQueue requestQueue;
    private List<order> orderList;
    private RecyclerView recyclerView;
    Button button,button1;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String status = "未送出";
    String alreadyorder = "0";
    String check;

    String data;
    String asd;  // try try 看

    int aaa;
     public static String lastid;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order1);
        final Spinner name = (Spinner) findViewById(R.id.ordername);
        button = (Button) findViewById(R.id.create);
        check=login.check;
        final Date dt =new Date();
        recyclerView = findViewById(R.id.orderview);
        orderList = new ArrayList<>();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("訂單");
        fetchData process = new fetchData();
        process.execute();

        jsonrequest();


        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
              final  String dts=sdf.format(dt);
                final String sellername =name.getSelectedItem().toString();

               StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/order/create", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplication(), "已送出!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(order1.this, home.class);
                        order1.this.finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(order1.this);
                        builder.setMessage("送出失敗!")
                                .setNegativeButton("再試一次!", null)
                                .create()
                                .show();
                    }
                }) {  //試試看
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("sellname",sellername);
                        map.put("buyname",check);
                        map.put("status",status);
                        map.put("ordertime",dts);
                        map.put("alreadyorder",alreadyorder);
                        map.put("orderdetailid",lastid);  // ok 可以嗎//yes 對了 還有一個更改的

                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(order1.this);
                queue.add(stringRequest1);


//可是那筆被我改過0.0  你有第一筆資料嗎 喔喔 有阿 撈那個資料表的orderdetail 等個   要是我產生一個訂單 能馬上到剛剛那頁嗎 要重進一次  大概知道了 我想一下
            }

            });
 //order_no這個? 對.....抱歉
        }
    private void jsonrequest() {

   request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
       @Override
       public void onResponse(JSONArray response) {

           JSONObject jsonObject = null;
           for(int i = 0 ;i < response.length();i++ ) {
               try {
                   jsonObject = response.getJSONObject(i);
                   order order = new order();
                     if(jsonObject.getString("buyname").equals(check)&&jsonObject.getString("status").equals(status)) {
                         order.setSelname(jsonObject.getString("sellname"));
                         order.setBuyname(jsonObject.getString("buyname"));
                         order.setOrdertime(jsonObject.getString("ordertime"));
                         order.setStatus(jsonObject.getString("status"));
                         order.setOrderdetail(jsonObject.getString("orderdetailid"));
                         order.setOrderid(jsonObject.getString("order_no"));
                         orderList.add(order);
                     }
                   }catch(JSONException e){
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

requestQueue = Volley.newRequestQueue(order1.this);
requestQueue.add(request);
    }

    private void setuprecycleview(List<order> orderList) {

        orderAdapter orderAdapter =  new orderAdapter(this,orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(orderAdapter);

    }
    class fetchData extends AsyncTask<Void,Void,Void> {
        String data="";
        String name ="";

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                URL url = new URL("http://140.126.146.28/dindong/api/order/findall");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }



                JSONArray JA = new JSONArray(data);                     //顯示最後一筆的u_id  API那邊有問題? 0.0 這樣看好像是欸 我重更新一下 你剛剛那個值式int?  他式string? 我設nchar ok
                JSONObject jo = JA.getJSONObject(JA.length()-1);
                asd = jo.get("orderdetailid").toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            aaa = Integer.parseInt(asd)+1;
            lastid = String.valueOf(aaa);




            //id.toString().matches(aaa);  ??? 怎麼了 0.0 //他說失敗欸 網址問題嗎 你的create ? //奇怪 我之前可以直接存0.0 !!
        }
    }

}
