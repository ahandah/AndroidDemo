package com.lz.mydemo01.listAd;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lz.mydemo01.AdapterBean;
import com.lz.mydemo01.R;
import com.lz.mydemo01.server.MyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView list;

    private MyService.MyBind myBind;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBind = (MyService.MyBind) service;
            System.out.println("-------------a----- " + myBind.getA());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(manager);
        List<MyBean> data = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            data.add(new MyBean(i, "msg"));
        }
        list.setAdapter(new MyAdapter(R.layout.list_item, data));

        System.out.println("Thread Name " + Thread.currentThread());

        Intent server = new Intent(this, MyService.class);
//        this.startService(server);

        bindService(server, connection, BIND_AUTO_CREATE);
        startService(server);


//        System.out.println(myBind.getA());

        try {
            Thread.sleep(2000);
            unbindService(connection);
            stopService(server);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyBean {
    private int id;
    private String msg;

    public MyBean(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class MyAdapter extends BaseQuickAdapter<MyBean, BaseViewHolder> {


    public MyAdapter(int layoutResId, @Nullable List<MyBean> data) {
        super(layoutResId, data);
        System.out.println(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBean item) {
        helper.setText(R.id.tv, item.getId() + "");
        if (item.getId() == 2) {
            helper.setVisible(R.id.list_item, false);
        } else {
            helper.setVisible(R.id.list_item, true);
        }
    }
}