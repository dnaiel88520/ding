package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class seller_register extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);
        final EditText sellname = (EditText) findViewById(R.id.sellername);
        final EditText sellaccount = (EditText) findViewById(R.id.sellaccount);
        final EditText sellpassword = (EditText) findViewById(R.id.sellpassword);
        final EditText selladdress = (EditText) findViewById(R.id.selladdress);
        final EditText sellphone = (EditText) findViewById(R.id.sellphone);
        final Button display = (Button)findViewById(R.id.button15);
        Button nextPageBtn2 = (Button)findViewById(R.id.btn_next);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("賣家註冊");

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellname.setText("立亨");
                sellaccount.setText("kevin");
                sellpassword.setText("daniel123");
                selladdress.setText("桃園市八德區陸光街");
                sellphone.setText("0909985250");
            }
        });

        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String status =String.valueOf(0);
                final  String name = sellname.getText().toString();
                final  String account = sellaccount.getText().toString();
                final  String password = sellpassword.getText().toString();
                final  String address = selladdress.getText().toString();
                final  String phone = sellphone.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    sellname.setError("輸入名字");
                    sellname.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(account)) {
                    sellaccount.setError("輸入帳號");
                    sellaccount.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    sellpassword.setError("輸入密碼");
                    sellpassword.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    selladdress.setError("輸入地址");
                    selladdress.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    sellphone.setError("輸入電話");
                    sellphone.requestFocus();
                    return;
                }
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/registered/create", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(), "請等待驗證", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(seller_register.this, login.class);
                        finish();
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(seller_register.this,"註冊失敗",Toast.LENGTH_LONG).show();
                        sellaccount.setError("帳號已被使用");
                        sellaccount.requestFocus();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("usname", name);
                        map.put("usaccount", account);
                        map.put("uspassword", password);
                        map.put("usaddress", address);
                        map.put("usphone", phone);
                        map.put("usstatus", status);
                        return map;
                    }
 
                };
                RequestQueue queue = Volley.newRequestQueue(seller_register.this);
                queue.add(stringRequest1);
                StringRequest stringRequest2 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/sellers/create", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("usname", name);
                        map.put("usaccount", account);
                        map.put("uspassword", password);
                        map.put("usaddress", address);
                        map.put("usphone", phone);
                        return map;
                    }

                };
                RequestQueue Queue = Volley.newRequestQueue(seller_register.this);
                Queue.add(stringRequest2);
            }
        });
        }
}
