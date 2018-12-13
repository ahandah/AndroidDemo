package com.example.administrator.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabHostActivity extends AppCompatActivity {

    private TabHost tabhost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

//        tabhost = getTab

        tabhost.addTab(tabhost
                //创建新标签one
                .newTabSpec("one")
                //设置标签标题
                .setIndicator("红色")
                //设置该标签的布局内容
//                .setContent(R.id.widget_layout_red)
 );
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("黄色")
//                .setContent(R.id.widget_layout_yellow)
        );
    }
}
