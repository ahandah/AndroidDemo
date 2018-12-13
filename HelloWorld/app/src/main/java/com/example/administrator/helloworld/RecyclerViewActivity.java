package com.example.administrator.helloworld;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.helloworld.MyView.MyDripView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.Inflater;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rV;
    private ArrayList<String> strings = new ArrayList<>();
    private MyAdapter myAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ItemTouchHelper itemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        rV = findViewById(R.id.rV);
//        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rV.setLayoutManager(manager);
        for (int i = 0; i < 15; i ++) {
            strings.add("i = " + i);
        }
        myAdapter = new MyAdapter();
        rV.setAdapter(myAdapter);
        rV.setNestedScrollingEnabled(false);

        itemTouchHelper = new ItemTouchHelper(myAdapter.callback);
        itemTouchHelper.attachToRecyclerView(rV);

    }

    class MyAdapter extends RecyclerView.Adapter {

        private ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags =0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                //得到当拖拽的viewHolder的Position
                int thisPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int targetPosition = target.getAdapterPosition();
                swipeItem(thisPosition, targetPosition);
//                notifyItemMoved(thisPosition, targetPosition);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//                drop
            }
        };
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.view_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvContext.setText(strings.get(position));
            viewHolder.tvContext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("data----"+strings.get(position));
                }
            });
            viewHolder.btn.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        itemTouchHelper.startDrag(viewHolder);
                    }
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        public void swipeItem(int first, int last) {
            notifyItemMoved(first, last);
            strings.set(first, strings.get(first));
            strings.set(last, strings.get(last));
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView tvContext;
            private TextView btn;
            public ViewHolder(View itemView) {
                super(itemView);
                tvContext = itemView.findViewById(R.id.tvContext);
                btn = itemView.findViewById(R.id.btn);
            }
        }

    }
}