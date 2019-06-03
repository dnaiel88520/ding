package com.example.asus.myapplication;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myapplication.activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class login extends AppCompatActivity  {
    TextView textView;
    RequestQueue rq;
    EditText edit_ID, edit_pw;
    private RequestQueue mQueue;
    private static final String TAG = "MainActivity";
    String usAccount, usPassword,status;
    String buy;
    String sell;

    public static String usid;   // 但我怕那頁街道的值不是撈出來的 而是這個 //先試看看
    public static  String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        final RequestQueue queue = Volley.newRequestQueue(this);

        edit_ID = (EditText) findViewById(R.id.edit_ID);
        edit_pw = (EditText) findViewById(R.id.edit_pw);
        Button nextPageBtn = (Button) findViewById(R.id.btn_register);
        Button nextPageBtn2 = (Button) findViewById(R.id.btn_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("登入");

        textView = findViewById(R.id.textView);


        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,register.class));
            }
        });




        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(login.this,register.class));
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String password = edit_pw.getText().toString();
                final  String account = edit_ID.getText().toString();

                if (TextUtils.isEmpty(account)) {
                    edit_ID.setError("輸入帳號");
                    edit_ID.requestFocus();
                }
                if (TextUtils.isEmpty(password)) {
                    edit_pw.setError("輸入密碼");
                    edit_ID.requestFocus();
                }
                JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, "http://140.126.146.28/dindong/api/registered/findall", new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                usid = jsonObject.getString("usid");
                                usAccount = jsonObject.getString("usaccount");
                                usPassword = jsonObject.getString("uspassword");
                               check = jsonObject.getString("usname");
                               status = jsonObject.getString("usstatus");
                                if(status.equals("0")){
                                    Toast.makeText(getApplication(), "還未進行認證!!!", Toast.LENGTH_SHORT).show();

                                }else if(status.equals("3")){
                                    Toast.makeText(getApplication(), "已被停權!!!", Toast.LENGTH_SHORT).show();

                                }else {

                                }
//上面式順序@@  蝦 0.0?  等我一下喔 // 啊我好像知道了= = 你先弄 0.0? 你弄好了 我發現沒錯  不曉得這個方法可不可以 我很少用這種傳值 但這種最快 //這樣能直接傳到那個頁面? 恩恩 前提你前面要這樣子

                                if (usAccount.equals(edit_ID.getText().toString().trim()) && usPassword.equals(edit_pw.getText().toString().trim())) {
                                    if (status.equals("1")){
                                        Intent intent = new Intent(login.this, home.class);
                                        Toast.makeText(getApplication(), "登入成功，歡迎你"+check+"!", Toast.LENGTH_SHORT).show();
                                       // Bundle bundle = new Bundle();
                                       // bundle.putString("buyname",check);
                                        //bundle.putString("usid",usid);  //這頁直接傳到那頁嗎?沒欸 中間一大堆0.0
                                       // intent.putExtras(bundle);
                                        finish();
                                        startActivity(intent);
                                        break;
                                    } else if ( status.equals("2")) {
                                        Intent intent = new Intent(login.this, seller_home.class);
                                        Toast.makeText(getApplication(), "登入成功，歡迎你"+check+"!", Toast.LENGTH_SHORT).show();
                                     //  Bundle bundle = new Bundle();
                                     //   bundle.putString("sellname",check);
                                      //  intent.putExtras(bundle);

                                        startActivity(intent);
                                        login.this.finish();
                                        break;
                                    }
                                } else if(!usAccount.equals(edit_ID.getText().toString().trim()) && !usPassword.equals(edit_pw.getText().toString().trim())) {


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Error", error.getMessage());
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(login.this);
                        builder.setMessage("登入失敗,檢查網路")
                                .setNegativeButton("請再試一次!", null)
                                .create()
                                .show();
                    }

                });
                queue.add(arrayRequest);
            }
        });

    }
            }
