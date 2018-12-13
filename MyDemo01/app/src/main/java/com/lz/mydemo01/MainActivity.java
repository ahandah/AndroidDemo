package com.lz.mydemo01;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyPagerAdapter myPagerAdapter;
//    private LoopViewPager viewPager;
    private ViewPager viewPager;
    private PageIndicatorView pageIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPagerAdapter = new MyPagerAdapter();

        viewPager = findViewById(R.id.viewPager);
        pageIndicatorView = findViewById(R.id.pageIndicatorView);

        viewPager.setAdapter(myPagerAdapter);

    }

    class MyPagerAdapter extends PagerAdapter {

        List<String> list = new ArrayList<>();

        public MyPagerAdapter() {
            list.add("1111");
            list.add("2222");
            list.add("3333");
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpage_item, null);
            TextView tv = view.findViewById(R.id.tv);
            tv.setText(list.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
