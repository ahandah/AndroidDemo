package com.smonkey.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class ThirdBannerActivity extends AppCompatActivity {

    private List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_banner);

        initData();

        Banner banner = findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.BackgroundToForeground);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles("");
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();

    }

    private void initData() {
        images = new ArrayList<>();
        images.add(R.drawable.ic_launcher);
        images.add(R.drawable.ic_launcher);
        images.add(R.drawable.ic_launcher);
    }
}
