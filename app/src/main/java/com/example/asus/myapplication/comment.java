package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
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

public class comment extends AppCompatActivity {
    private ImageButton imgbtnback;
    Spinner spin_seller;
    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        check=login.check;
        spin_seller = (Spinner) findViewById(R.id.spin_seller);
        final EditText txv_comm = (EditText) findViewById(R.id.txv_comm);
        final Button btn_ok = (Button) findViewById(R.id.btn_ok);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("評論");



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String comm = txv_comm.getText().toString();
                final String sell = spin_seller.getSelectedItem().toString();

                if(TextUtils.isEmpty(comm)){
                    txv_comm.setError("請輸入評論");
                    txv_comm.requestFocus();
                    return;
                }

                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/comment/create", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(), "評論已送出!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(comment.this, setting.class);
                        finish();
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(comment.this);
                        builder.setMessage("送出失敗!")
                                .setNegativeButton("再試一次!", null)
                                .create()
                                .show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("buyname",check);
                        map.put("commenttext",comm);
                        map.put("commentname",sell);
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(comment.this);
                queue.add(stringRequest1);
            }
        });
    }
}
