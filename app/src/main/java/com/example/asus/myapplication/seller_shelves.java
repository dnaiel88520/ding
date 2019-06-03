package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class seller_shelves extends AppCompatActivity {
String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_shelves);
        final EditText proname = (EditText) findViewById(R.id.proname);
        final EditText proprice = (EditText) findViewById(R.id.proprice);
        final EditText procontext = (EditText) findViewById(R.id.procontext);
        final Button btn_ok = (Button) findViewById(R.id.button14);
        final Button pro=(Button)findViewById(R.id.button9) ;
         check = login.check;
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("新增飲品");

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proname.setText("冰淇淋紅茶");
                proprice.setText("50");
                procontext.setText("冰淇淋很好吃的，紅茶很好喝!!!!!");
            }
        });



        btn_ok.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          final String name = proname.getText().toString();
                                          final String price = proprice.getText().toString();
                                          final String context = procontext.getText().toString();
                                          StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/produces/create", new Response.Listener<String>() {
                                              @Override
                                              public void onResponse(String response) {
                                                  Toast.makeText(getApplication(), "已新增!", Toast.LENGTH_SHORT).show();
                                                  seller_shelves.this.finish();
                                              }
                                          }, new Response.ErrorListener() {
                                              @Override
                                              public void onErrorResponse(VolleyError error) {
                                                  AlertDialog.Builder builder = new AlertDialog.Builder(seller_shelves.this);
                                                  builder.setMessage("新增失敗!")
                                                          .setNegativeButton("再試一次!", null)
                                                          .create()
                                                          .show();
                                              }
                                          }) {
                                              @Override
                                              protected Map<String, String> getParams() throws AuthFailureError {
                                                  Map<String, String> map = new HashMap<String, String>();
                                                  map.put("proname", name);
                                                  map.put("prodata", context);
                                                  map.put("proprice", price);
                                                  map.put("sellname",check);
                                                  return map;
                                              }
                                          };
                                          RequestQueue queue = Volley.newRequestQueue(seller_shelves.this);
                                          queue.add(stringRequest1);
                                      }
                                  }
        );
    }
}





