package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class changepw extends AppCompatActivity {

    private Button button2;
    EditText  newpasw1;
   String seller;
    TextView name;
    String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepw);
        final RequestQueue queue = Volley.newRequestQueue(this);
        check=login.check;
        newpasw1 = (EditText) findViewById(R.id.newpasw);
        name = (TextView) findViewById(R.id.name);
        button2 =(Button)findViewById(R.id.button2);
        name.setText(check);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("密碼變更");







    button2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        String url = "http://140.126.146.28/dindong/api/registered/update";

      final  StringRequest mStringRequest = new StringRequest(Request.Method.PUT,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplication(), "修改成功!", Toast.LENGTH_SHORT).show();
                    changepw.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), "修改失敗，請查看!!", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                String newpas = newpasw1.getText().toString();
                params.put("usname", check);
                params.put("uspassword", newpas);
                return params;
            }

        };

        queue.add(mStringRequest);
    }
});

    }
}