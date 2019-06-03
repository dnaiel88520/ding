package com.example.asus.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class history extends AppCompatActivity {
    TextView history;
    String check;
    String status = "已完成";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("歷史查看");
        history = findViewById(R.id.history);
        check=login.check;
        new getData().execute();

    }

    class getData extends AsyncTask<String, String, String> {
        private String LOCALHOST = "140.126.146.28";
        HttpURLConnection urlConnection = null;


        protected String doInBackground(String... args) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL("http://" + LOCALHOST + "/dindong/api/order/findall");

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
                    if (check.equals(jsonObject.getString("buyname")) && status.equals(jsonObject.getString("status"))) {
                        String a = jsonObject.getString("order_no");
                        String b = jsonObject.getString("sellname");
                        String c = jsonObject.getString("ordertime");
                        String d = jsonObject.getString("buyname");
                        String e = jsonObject.getString("status");
                        history.append("飲料編號:" + a + "\n"
                                + "賣方:" + b + "\n"
                                + "訂購時間:" + c + "\n"
                                + "買方:" + d + "\n"
                                + "狀態:" + e + "\n"
                                + "\n");
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}