package com.example.asus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class orderdetail extends AppCompatActivity {
    Spinner spin1, spin2, spin3, spin4;
    private List<Map<String, Object>> data;
    Button add, delete, post;
    TextView txv_temp, txv_cup;
    ImageButton imgbtnback;
    String seller;
    String orderstatus ="已送出";
    String alreadyorder =String.valueOf(1);
    TextView order_no;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        imgbtnback = (ImageButton) findViewById(R.id.btnback7);
        final RequestQueue queue = Volley.newRequestQueue(this);
        Intent id = getIntent();
        seller = id.getStringExtra("buyname");

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(orderdetail.this, home.class);
                Bundle bundle = new Bundle();
                bundle.putString("buyname", seller);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


        String sellname = getIntent().getExtras().getString("sellername");
       final String orderstatus = getIntent().getExtras().getString("status");
      final String alreadyorder = getIntent().getExtras().getString("alreadyorder");
        final String orderid = getIntent().getExtras().getString("order_no");
        final String orderdetail =  getIntent().getExtras().getString("orderdetail");


//  上面那3個個別是什麼id 第一個流水號 第2個想要拿前面的訂單編號 第三個是拿前面的明細編號  那前面那頁
        //所以你要post的時候 一次多筆然後 都是同個編號嗎 ㄜ 不用那麼麻煩 是想要加入的時候就進資料庫了

        spin1 = (Spinner) findViewById(R.id.spin_seller);
        spin2 = (Spinner) findViewById(R.id.spin_drink);
        spin3 = (Spinner) findViewById(R.id.spin_suger);
        spin4 = (Spinner) findViewById(R.id.spin_ice);
        txv_cup = (TextView) findViewById(R.id.txv_cup);
        txv_temp = (TextView) findViewById(R.id.txv_temp);
        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);
        post = (Button) findViewById(R.id.post);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        spin1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String[] tempSet;
                if (arg2 == 5)
                    tempSet = getResources().getStringArray(R.array.milkshop);
                else if (arg2 == 4)
                    tempSet = getResources().getStringArray(R.array.alley);
                else if (arg2 == 3)
                    tempSet = getResources().getStringArray(R.array.heart);
                else if (arg2 == 2)
                    tempSet = getResources().getStringArray(R.array.dayung);
                else if (arg2 == 1)
                    tempSet = getResources().getStringArray(R.array.teasoup);
                else
                    tempSet = getResources().getStringArray(R.array.coco);
                ArrayAdapter<String> tempAd = new ArrayAdapter<String>(orderdetail.this, android.R.layout.simple_spinner_item, tempSet);
                tempAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin2.setAdapter(tempAd);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                final String cup = txv_cup.getText().toString();
                if (TextUtils.isEmpty(cup)) {
                    txv_cup.setError("輸入數量");
                    txv_cup.requestFocus();
                    return;
                }
                String msg = spin2.getSelectedItem().toString() + spin3.getSelectedItem().toString() + spin4.getSelectedItem().toString() + txv_cup.getText().toString() + "杯" + "\n";
                txv_temp.append(msg);
                final String seller = spin1.getSelectedItem().toString();
                final String drink = spin2.getSelectedItem().toString();
                final String sugar = spin3.getSelectedItem().toString();
                final String ice = spin4.getSelectedItem().toString();
                //final String cup = txv_cup.getText().toString();

                if (TextUtils.isEmpty(cup)) {
                    txv_cup.setError("輸入數量");
                    txv_cup.requestFocus();
                    return;   //所以你們明細資料表 剛剛新增一筆  就要有同樣的編號? 就是按那個加入  新增在明細?摁摁 新增在textview  阿你的明細 能看個findall嗎 裡面還沒有資料0.0 納蘭為 借我看個
                }    // 這個url 有findall嗎 0.0等一下 url好像錯了//這是訂單明細的url 完蛋  我訂單和明細搞不清楚 好 等我一下// 這頁的新增 是新增訂單 也會跟著新增明細編號 你操作一次 就是加入的時候 明細編號要相同 oh 懂了
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/orderdetail/create", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {//這段應該不能回到主頁@@ 能 在同業嗎
                        Toast.makeText(getApplication(), "已加入!", Toast.LENGTH_SHORT).show();
                       /* Intent intent = new Intent(orderdetail.this, home.class);
                        finish();
                        startActivity(intent);*/
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(orderdetail.this);
                        builder.setMessage("送出失敗!")
                                .setNegativeButton("再試一次!", null)
                                .create()
                                .show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();

                        map.put("orderid",orderid.trim());
                        map.put("orderdetailid",orderdetail.trim());
                        map.put("sellname", seller.trim());
                        map.put("proname", drink.trim());
                        map.put("sugar", sugar.trim());
                        map.put("ice", ice.trim());
                        map.put("quantity", cup.trim());  //等一下  我要換按鈕0.0 這顆按鈕式確認的 哈哈  是這樣嗎 對!!!  因為你們的欄位沒有用nvarchar
                        // 所以後面會有剩餘空間   +trim() 可以把那些後面的東西/扣掉//懂了!!!阿改狀態是要看update?  恩恩 或者你可以寫成是 status別塞資料 讓她空值
                        //判斷他為空值 就顯示 未送出  你這裡確認送出 再直接新增資料庫status資料  判斷有值再顯示 已送出 好的 大概明白 //啊我要把這訂單送到應對應的賣家有很困難嗎0.0
                        //應該對應的? 一開始選的那個 哪個 0.0 ㄜ 不太懂  什麼送到那邊 ㄜ 應該是按送出之後 會寄到那個店家 你是說跑到這個裡面嗎?我現在是買家0.0 近來加入完再按送出 然後訂單會送到賣家 阿賣家你要在哪顯示0.0?
                        //再賣家那邊有頁面 訂單明細資料的話 就撈資料庫吧 應該是這樣 那我也要再另外寫recycleview給賣家 然後點進去是詳細資料 跟確認作完的按鈕這樣?   之後你可能要找欄位判斷 你是哪個店家  那個就是賣家對吧 摁摁  登入的時候有撈他的姓名了  恩恩那就是用那個判斷
                        //0.0 ? 我想一下 我好像沒做到算錢的= = oops OuO 算錢是不是有困難 哈哈 要拿產品的資料 對呀 拿大有算錢 我記得 可是我這邊的關聯鍵沒設定
                        //欸= =  你藍未有價錢 嗎 有  有點複雜0.0 我有點母湯了 摁摁 我再問看看拿大吧 我要去就寢了 摁摁 好的 感謝喔!!!
                        //  恩恩 加油啦 好的  晚安晚安
                        return map;
                    }
                };

                queue.add(stringRequest1);
            }
        });

        delete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                txv_temp.setText("");
            }
        });



        post.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                StringRequest stringRequest1 = new StringRequest(Request.Method.PUT, "http://140.126.146.28/dindong/api/order/update", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(), "已送出!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(orderdetail.this, home.class);
                        finish();
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(orderdetail.this);
                        builder.setMessage("送出失敗!")
                                .setNegativeButton("再試一次!", null)
                                .create()
                                .show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        final String alreadyorder =String.valueOf(1);
                        final String orderstatus ="已送出";

                        Map<String, String> map = new HashMap<String, String>();
                        map.put("alreadyorder",alreadyorder.trim());
                        map.put("status",orderstatus.trim());
                        return map;
                    }
                };

                queue.add(stringRequest1);
            }
        });
    }
}





