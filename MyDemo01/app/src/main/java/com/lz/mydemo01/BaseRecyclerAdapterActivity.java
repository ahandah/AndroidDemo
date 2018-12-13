package com.lz.mydemo01;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BaseRecyclerAdapterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  MyQuickAdapter myQuickAdapter;
    private SmartRefreshLayout smartRefreshLayout;
    private List<String> list = new ArrayList<>();
    private List<AdapterBean> list2 = new ArrayList<>();
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler_adapter);

        tv = findViewById(R.id.testTv);


        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        for (int i = 0; i < 10; i ++) {
            list.add("item - " + i);
        }
        for (int i = 0; i < 10; i ++) {
            if (i % 2 == 0) {
                list2.add(new AdapterBean(true, "item - " + i));
            } else {
                list2.add(new AdapterBean(false, "item - " + i));
            }

        }

//        myQuickAdapter = new MyQuickAdapter(R.layout.list_item, list);
        myQuickAdapter = new MyQuickAdapter(R.layout.list_item, R.layout.list_item_footer_header, list2);
        recyclerView.setAdapter(myQuickAdapter);
        myQuickAdapter.isFirstOnly(false);

        myQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                myQuickAdapter.loadMoreEnd();
                list2.add(new AdapterBean( "item - add new " ));
//                myQuickAdapter.loadMoreComplete();
            }
        }, recyclerView);


    }

//    class MyQuickAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
//        public MyQuickAdapter(int layoutResId, @Nullable List<String> data) {
//            super(layoutResId, data);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder helper, String item) {
//            helper.setText(R.id.tv, item);
////            .addOnClickListener(R.id.list_item)
////            .addOnClickListener(R.id.tv);
//        }
//
//
//    }

    public class MyQuickAdapter extends BaseSectionQuickAdapter<AdapterBean, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param layoutResId      The layout resource id of each item.
         * @param sectionHeadResId The section head layout id for each item
         * @param data             A new list is created out of this one to avoid mutable list
         */
        public MyQuickAdapter(int layoutResId, int sectionHeadResId, List<AdapterBean> data) {
            super(layoutResId, sectionHeadResId, data);
        }

        @Override
        protected void convertHead(BaseViewHolder helper, AdapterBean item) {
            helper.setText(R.id.headerTv, "header " + item.header);
        }

        @Override
        protected void convert(BaseViewHolder helper, AdapterBean item) {
            System.out.println(item);
            helper.setText(R.id.tv, "content " + item.t);
        }
    }


}
