package com.example.administrator.helloworld;

import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.helloworld.eventBus.FirstEventBus;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;
    private ArrayList<String> arrayList;
    private MyAdapter myAdapter;
    private Button sendFirstActivity, addData, removeData;
    private SwipeRefreshLayout swipRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sendFirstActivity = findViewById(R.id.sendFirstActivity);
        sendFirstActivity.setOnClickListener(this);
        addData = findViewById(R.id.addData);
        addData.setOnClickListener(this);
        removeData = findViewById(R.id.removeData);
        removeData.setOnClickListener(this);

        swipRefresh = findViewById(R.id.swipRefresh);
        swipRefresh.setOnRefreshListener(this);

        mRecyclerView = findViewById(R.id.recyclerView);
        arrayList = new ArrayList<String>();
        for (int i = 1 ; i < 11 ; i++){
            arrayList.add(i+"");
        }
        myAdapter = new MyAdapter(arrayList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayout);
        mRecyclerView.setAdapter(myAdapter);
//        监听mRecyclerView滑动,实现下拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                final LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//                判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==myAdapter.getItemCount()){

                    swipRefresh.setRefreshing(true);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            for (int i = 0; i< 10; i++) {

                                myAdapter.addData2(myAdapter.getItemCount(), "footer  item" + i);
                            }

                            swipRefresh.setRefreshing(false);
                            Toast.makeText(Main2Activity.this, "更新了 "+10+" 条目数据", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.addData:
                myAdapter.addData(0);
                mRecyclerView.smoothScrollToPosition(0);
                break;
            case R.id.removeData:
                myAdapter.removeData(0);
                break;
            case R.id.sendFirstActivity:
                EventBus.getDefault().post(new FirstEventBus("sendFirstActivity!!!"));
//                EventBus.getDefault().po
                break;

//            case R.id.recyclerView:
//                break;
        }
    }
//下拉刷新监听
    @Override
    public void onRefresh() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                    System.out.println("---3000---");
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            swipRefresh.setRefreshing(false);
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        swipRefresh.setRefreshing(false);
    }

    class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private ArrayList<String> arrayList ;
        public MyAdapter(ArrayList<String> arrayList){
            this.arrayList = arrayList;
        }

        public void addData(int position){

            arrayList.add(position, "addData");
//            myAdapter.notifyItemInserted(position);
            myAdapter.notifyItemInserted(position);
//            全部刷新
//            myAdapter.notifyDataSetChanged();
//            局部刷新
            if (position != arrayList.size()){
                myAdapter.notifyItemRangeChanged(position, arrayList.size() - position);
            }

        }

        public void addData2(int position, String s){

            arrayList.add(position, s);
//            myAdapter.notifyItemInserted(position);
            myAdapter.notifyItemInserted(position);
//            全部刷新
//            myAdapter.notifyDataSetChanged();
//            局部刷新
            if (position != arrayList.size()){
                myAdapter.notifyItemRangeChanged(position, arrayList.size() - position);
            }

        }

        public void removeData(int position){

            arrayList.remove(position);
//            myAdapter.notifyItemInserted(position);
            myAdapter.notifyItemRemoved(position);
//            全部刷新
//            myAdapter.notifyDataSetChanged();
//            局部刷新
            if (position != arrayList.size()){
                myAdapter.notifyItemRangeChanged(position, arrayList.size() - position);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(Main2Activity.this)
                    .inflate(R.layout.item_recycler, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.tv.setText(arrayList.get(position));
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(arrayList.get(position)+"---data---"+position);

                }
            });
        }


        @Override
        public int getItemCount() {
            return arrayList == null ? 0 : arrayList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView tv;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }

    }
}
