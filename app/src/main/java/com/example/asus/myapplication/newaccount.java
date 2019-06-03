package com.example.asus.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

public class newaccount extends AppCompatActivity  {
     TextView textView14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);
        fetchData process = new fetchData();
        process.execute();

    }
        class fetchData extends AsyncTask<Void, Void, Void> {
            String data = "";
            String dataParsed = "";
            String singleParsed = "";
            String fb_findnumber = "";
            String asd = "";      // up
           String name = "";   // up
            String account1="";
            String password1="";

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    URL url = new URL("http://140.126.146.28/dindong/api/registereds/findall");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while (line != null) {
                        line = bufferedReader.readLine();
                        data = data + line;
                    }

                    JSONArray JA = new JSONArray(data);       //迴圈顯示每筆資料


                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject J0 = (JSONObject) JA.get(i);

                        name=J0.get("usaccount").toString().trim();

                        textView14.append(name + "\n");
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;

            }


        }
}
