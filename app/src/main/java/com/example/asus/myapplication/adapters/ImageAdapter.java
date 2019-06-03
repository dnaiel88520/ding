package com.example.asus.myapplication.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    private Context context;
    //輪播需要的圖片
    public ArrayList<ImageView> imgs;

    public ImageAdapter(Context context, ArrayList<ImageView> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    /**
     * ViewPager的邊界
     * @return
     */
    @Override
    public int getCount() {
        //設置成最大，使無限循環
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 由於我們在instantiateItem()方法中已經處理了remove的邏輯，
     * 因此這裏並不需要處理。實際上，實驗表明這裏如果加上了remove的調用，
     * 則會出現ViewPager的內容為空的情況。
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //警告:不要在這裏調用removeView
    }

    /**
     * @param container
     * @param position
     * @return
     * 對position進行求模操作
     * 因為當用戶向左滑時position可能出現負值，所以必須進行處理
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //對Viewpager頁號求模去除View列表中要顯示的項
        position %= imgs.size();
        if (position<0) {
            position = imgs.size() + position;
        }
        ImageView view = imgs.get(position);
        //如果View已經在之前添加到了一個父組件，則必須先remove，否則會拋出IllegalStateException。

        ViewParent viewParent = view.getParent();
        if (viewParent!=null){
            ViewGroup parent = (ViewGroup)viewParent;
            parent.removeView(view);
        }
        container.addView(view);


        return view;
    }
}
