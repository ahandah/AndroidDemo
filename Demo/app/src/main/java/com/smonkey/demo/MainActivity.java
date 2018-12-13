package com.smonkey.demo;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.smonkey.demo.adapter.MyAdapter;
import com.smonkey.demo.viewpage.LoopViewPager;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

//    private LoopViewPager banner;
    private ViewPager banner;
    private BannerAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RequestOptions options = new RequestOptions();
//        options.error(R.drawable.aaa);
//
//        Glide.with(this).load("http://img2.3lian.com/img2007/10/24/011.jpg")
//                .apply(options)
//                .into((ImageView) findViewById(R.id.iv));
//
//        banner = findViewById(R.id.banner);
//        adapter = new BannerAdapter();
//        banner.setAdapter(adapter);
//        banner.setPageMargin(100);
//        banner.setOffscreenPageLimit(4);
//
//        findViewById(R.id.ll).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return banner.dispatchTouchEvent(event);
//            }
//        });

//        //华为角标提示
//        Bundle bundle = new Bundle();
//        bundle.putString("package", getPackageName());
//        String launchClassName = getPackageManager().getLaunchIntentForPackage(getPackageName()).getComponent().getClassName();
//        bundle.putString("class", launchClassName);
//        bundle.putInt("badgenumber", 10);
//        getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bundle);

//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               String url = "https://www.baidu.com/";
//               OkHttpClient okHttpClient = new OkHttpClient();
//               Request request = new Request.Builder()
//                       .url(url)
//                       .build();
//               Call call = okHttpClient.newCall(request);
//               try {
//                   Response response = call.execute();
//                   System.out.println(response.body().string());
//               } catch (IOException e) {
//                   e.printStackTrace();
//               }
//           }
//       }).start();


        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this));

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            }
        });


    }

    public class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            System.out.println("viewpage ------- getCount");
            return 6;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            System.out.println("viewpage ------- isViewFromObject");
            return view == o;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            System.out.println("viewpage ------- destroyItem");
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            System.out.println("viewpage ------- instantiateItem");
            TextView tv = new TextView(MainActivity.this);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.height = 100;
            tv.setBackgroundColor(Color.RED);
            tv.setText("sdfsdfsdfs");
            container.addView(tv);
            return tv;
        }
    }
}
