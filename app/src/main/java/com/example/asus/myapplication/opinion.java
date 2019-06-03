package com.example.asus.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class opinion extends AppCompatActivity {
    TextView comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("觀看評論");
        comment = findViewById(R.id.comment);
        new opinion.getData().execute();

    }

    class getData extends AsyncTask<String, String, String> {
        private String LOCALHOST = "140.126.146.28";
        HttpURLConnection urlConnection = null;


        protected String doInBackground(String... args) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL("http://" + LOCALHOST + "/dindong/api/comment/findall");

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
                    String c = jsonObject.getString("buyname");
                    String b = jsonObject.getString("commenttext");
                    comment.append(c+"評論為:"+b+"\n");

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
