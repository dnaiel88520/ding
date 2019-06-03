package com.example.asus.myapplication;



import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class user_register extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        final EditText user_name = (EditText) findViewById(R.id.user_name);
        final EditText user_account = (EditText) findViewById(R.id.user_account);
        final EditText user_password = (EditText) findViewById(R.id.user_password);
        final EditText user_email = (EditText) findViewById(R.id.user_email);
        final EditText user_phone = (EditText) findViewById(R.id.user_phone);
        final Button btn_backlogin = (Button) findViewById(R.id.btn_backlogin);
        final Button display = (Button)findViewById(R.id.button7);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("買家註冊");

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name.setText("喜德");
                user_account.setText("daniel");
                user_password.setText("daniel123");
                user_email.setText("qazwsx123@gmail.com");
                user_phone.setText("0909985250");
            }
        });

        btn_backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String status = String.valueOf(1);
                final String name = user_name.getText().toString();
                final String account = user_account.getText().toString();
                final String password = user_password.getText().toString();
                final String email = user_email.getText().toString();
                final String phone = user_phone.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    user_name.setError("輸入名字");
                    user_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(account)) {
                    user_account.setError("輸入帳號");
                    user_account.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    user_password.setError("輸入密碼");
                    user_password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    user_email.setError("輸入email");
                    user_email.requestFocus();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    user_email.setError("輸入正確的email");
                    user_email.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    user_phone.setError("輸入電話");
                    user_phone.requestFocus();
                    return;
                }
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/registered/create", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(), "註冊成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(user_register.this, login.class);
                        finish();
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(user_register.this, "註冊失敗", Toast.LENGTH_LONG).show();
                        user_account.setError("帳號已被使用");
                        user_account.requestFocus();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("usname", name);
                        map.put("usaccount", account);
                        map.put("uspassword", password);
                        map.put("usemail", email);
                        map.put("usphone", phone);
                        map.put("usstatus", status);

                        return map;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(user_register.this);
                queue.add(stringRequest1);

                StringRequest stringRequest2 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/buy/create", new Response.Listener<String>() {
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
                        map.put("usemail", email);
                        map.put("usphone", phone);


                        return map;
                    }


                };
                RequestQueue Queue = Volley.newRequestQueue(user_register.this);
                Queue.add(stringRequest2);
            }
        });


    }
}