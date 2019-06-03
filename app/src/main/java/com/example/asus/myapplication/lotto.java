package com.example.asus.myapplication;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class lotto extends AppCompatActivity {


    //设置一个时间常量，此常量有两个作用，1.圆灯视图显示与隐藏中间的切换时间；2.指针转一圈所需要的时间，现设置为500毫秒
    private static final long ONE_WHEEL_TIME = 500;
    //记录圆灯视图是否显示的布尔常量
    private boolean lightsOn = true;
    //开始转动时候的角度，初始值为0
    private int startDegree = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    final Date dt =new Date();

    private ImageView lightIv;
    private ImageView pointIv;
    private ImageView wheelIv;
    private ImageButton imgbtnback;
    String check;
    String status = "未使用";
    //指针转圈圈数数据源
    private int[] laps = { 5, 7, 10, 15 };
    //指针所指向的角度数据源，因为有6个选项，所有此处是6个值
    private int[] angles = { 0, 60, 120, 180, 240, 300 };
    //转盘内容数组
    private String[] lotteryStr = { "買一送一", "折價5元", "折價10元", "折價15元",
            "銘謝惠顧", "銘謝惠顧", };

    //子线程与UI线程通信的handler对象
    private Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    if (lightsOn) {
                        // 设置lightIv不可见
                        lightIv.setVisibility(View.INVISIBLE);
                        lightsOn = false;
                    } else {
                        // 设置lightIv可见
                        lightIv.setVisibility(View.VISIBLE);
                        lightsOn = true;
                    }
                    break;

                default:
                    break;
            }
        };

    };

    //监听动画状态的监听器
    private AnimationListener al = new AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            final  String dts=sdf.format(dt);
           final String name = lotteryStr[startDegree % 360 / 60];
            Toast.makeText(lotto.this, name, Toast.LENGTH_LONG).show();
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://140.126.146.28/dindong/api/discount/create", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplication(), "折扣已送出!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(lotto.this, lotto.class);

                   lotto.this.finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(lotto.this);
                    builder.setMessage("送出失敗!")
                            .setNegativeButton("再試一次!", null)
                            .create()
                            .show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("discount_context",name);
                    map.put("discount_buyname",check);
                    map.put("status",status);
                    map.put("dtime",dts);
                    return map;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(lotto.this);
            queue.add(stringRequest1);
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("轉盤");
        check=login.check;
        setupViews();
        flashLights();
        final Date dt =new Date();
        pointIv.setOnClickListener(new OnClickListener() {
           int x=0;
            @Override
            public void onClick(View v) {
                if(x==0){


                int lap = laps[(int) (Math.random() * 4)];
                int angle = angles[(int) (Math.random() * 6)];
                //每次转圈角度增量
                int increaseDegree = lap * 360 + angle;
                //初始化旋转动画，后面的四个参数是用来设置以自己的中心点为圆心转圈
                RotateAnimation rotateAnimation = new RotateAnimation(
                        startDegree, startDegree + increaseDegree,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                //将最后的角度赋值给startDegree作为下次转圈的初始角度
                startDegree += increaseDegree;
                //计算动画播放总时间
                long time = (lap + angle / 360) * ONE_WHEEL_TIME;
                //设置动画播放时间
                rotateAnimation.setDuration(time);
                //设置动画播放完后，停留在最后一帧画面上
                rotateAnimation.setFillAfter(true);
                //设置动画的加速行为，是先加速后减速
                rotateAnimation.setInterpolator(lotto.this,
                        android.R.anim.accelerate_decelerate_interpolator);
                //设置动画的监听器
                rotateAnimation.setAnimationListener(al);
                //开始播放动画
                pointIv.startAnimation(rotateAnimation);
            }x++;
               if(x !=0)
                {
                    System.out.println("以達上限");
                }}


        });

    }

    private void setupViews(){
        lightIv = (ImageView) findViewById(R.id.light);
        pointIv = (ImageView) findViewById(R.id.point);
        wheelIv = (ImageView) findViewById(R.id.main_wheel);
    }

    //控制灯圈动画的方法
    private void flashLights() {

        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                // 向UI线程发送消息
                mHandler.sendEmptyMessage(0);
            }
        };

        // 每隔ONE_WHEEL_TIME毫秒运行tt对象的run方法
        timer.schedule(tt, 0, ONE_WHEEL_TIME);
    }

    }

