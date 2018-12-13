package com.example.administrator.helloworld;

import android.annotation.SuppressLint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends PagerAdapter {

        ArrayList<View> viewList = new ArrayList<>();
        @SuppressLint("ResourceType")
        public ViewPagerAdapter() {
            viewList.add(LayoutInflater.from(ViewPagerActivity.this).inflate(R.layout.view_ads_img, null));
            viewList.add(LayoutInflater.from(ViewPagerActivity.this).inflate(R.layout.view_ads_img, null));
        }
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            System.out.println("isViewFromObject----object--" + object);
            return view == viewList.get(Integer.parseInt(object.toString()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem--position--" + position);
            container.addView(viewList.get(position));
            return position;
        }
    }
}
