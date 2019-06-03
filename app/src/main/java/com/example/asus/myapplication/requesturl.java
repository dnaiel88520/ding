package com.example.asus.myapplication;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class requesturl extends StringRequest {
    private static final  String Register ="http://localhost/dindong/api/registereds/create";
    private Map<String,String> params;

    public requesturl(String user_account, String user_name,String user_password, String user_email,String user_phone, Response.Listener<String>listener)
    {
        super(Method.POST, Register,listener,null);
        params = new HashMap<>();
        params.put("user_account",user_account);
        params.put("user_name",user_name);
        params.put("user_password",user_password);
        params.put("user_email",user_email);
        params.put("user_phone",user_phone);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

