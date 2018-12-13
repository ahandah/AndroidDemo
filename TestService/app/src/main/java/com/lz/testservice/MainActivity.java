package com.lz.testservice;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yw.game.floatmenu.FloatItem;
import com.yw.game.floatmenu.FloatLogoMenu;
import com.yw.game.floatmenu.FloatMenuView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Button start, stop, exit;
    private Intent itService;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        init();
    }

    private void bindView() {
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        tv = findViewById(R.id.tv);
        exit = findViewById(R.id.exit);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {

        System.out.println("Global - height - " + Global.getStatusBarHeight(this));
        itService = (Intent) getSystemService(MyService.class.getName());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("start click");
                itService = new Intent(MainActivity.this, MyService.class);
//                bindService(itService);
                startService(itService);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("stop click");
                if (itService == null) {
                    itService = new Intent(MainActivity.this, MyService.class);
                }
                stopService(itService);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        if (! Settings.canDrawOverlays(MainActivity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent,10);
        }
        List<FloatItem> list = new ArrayList<>();
        list.add(new FloatItem("sdafsf", Color.BLACK, Color.BLACK, BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher), String.valueOf(0 + 1)));
        FloatLogoMenu floatLogoMenu = new FloatLogoMenu.Builder()
                .withActivity(MainActivity.this)
                .withContext(MainActivity.this.getApplication())//这个在7.0（包括7.0）以上以及大部分7.0以下的国产手机上需要用户授权，需要搭配<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
                .logo(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .drawCicleMenuBg(true)
                .backMenuColor( Color.BLACK)
                .setBgDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.ic_launcher))
                //这个背景色需要和logo的背景色一致
                .setFloatItems(list)
                .defaultLocation(FloatLogoMenu.RIGHT)
                .drawRedPointNum(false)
//                .showWithLogo(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                .showWithListener(new FloatMenuView.OnMenuClickListener() {
                    @Override
                    public void onItemClick(int position, String title) {
                        Toast.makeText(MainActivity.this, "FloatLogoMenu - click", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void dismiss() {

                    }
                });
//        floatLogoMenu.show();

    }

}
